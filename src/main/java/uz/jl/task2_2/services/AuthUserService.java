package uz.jl.task2_2.services;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.jl.task2_2.config.security.UserDetails;
import uz.jl.task2_2.domains.sub.ActivationCode;
import uz.jl.task2_2.domains.AuthUser;
import uz.jl.task2_2.domains.CustomAuthenticationManager;
import uz.jl.task2_2.dtos.*;
import uz.jl.task2_2.dtos.auth.AuthUserDTO;
import uz.jl.task2_2.dtos.auth.PasswordDTO;
import uz.jl.task2_2.exceptions.GenericInvalidTokenException;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.exceptions.GenericRuntimeException;
import uz.jl.task2_2.mappers.AuthUserMapper;
import uz.jl.task2_2.repository.AuthUserRepository;
import uz.jl.task2_2.services.auth.ActivationCodeService;
import uz.jl.task2_2.services.mail.MailService;
import uz.jl.task2_2.utils.jwt.TokenService;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.function.Supplier;

/**
 * @author "Isroilov Javohir"
 * @since 19/08/22/12:07 (Friday)
 * spring-boot-features/IntelliJ IDEA
 */

@Service

public class AuthUserService implements UserDetailsService {
//    private final AuthenticationManager authenticationManager;

    private final CustomAuthenticationManager authenticationManager;
    private final AuthUserRepository authUserRepository;
    private final TokenService accessTokenService;
    private final TokenService refreshTokenService;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final ActivationCodeService activationCodeService;

    @Value("${activation.link.base.path}")
    private String basePath;

    public AuthUserService(@Lazy CustomAuthenticationManager authenticationManager,
                           AuthUserRepository authUserRepository,
                           @Qualifier("accessTokenService") TokenService accessTokenService,
                           @Qualifier("refreshTokenService") TokenService refreshTokenService,
                           AuthUserMapper authUserMapper,
                           PasswordEncoder passwordEncoder,
                           MailService mailService,
                           ActivationCodeService activationCodeService) {
        this.authenticationManager = authenticationManager;
        this.authUserRepository = authUserRepository;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.authUserMapper = authUserMapper;

        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.activationCodeService = activationCodeService;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> exception = () ->
                new UsernameNotFoundException("Bad credentials");
        AuthUser authUser = authUserRepository.findByUsername(username).orElseThrow(exception);
        return new UserDetails(authUser);
    }

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = accessTokenService.generateToken(userDetails);
        String refreshToken = refreshTokenService.generateToken(userDetails);
        AuthUser authUser = userDetails.authUser();
        authUser.setLastLoginTime(LocalDateTime.now());
        authUserRepository.save(authUser);

        return new JwtResponse(accessToken, refreshToken, "Bearer");
    }


    public JwtResponse refreshToken(@NonNull RefreshTokenRequest request) {
        String token = request.token();
        if (accessTokenService.isValid(token)) {
            throw new GenericInvalidTokenException("Refresh Token invalid", 401);
        }
        String subject = accessTokenService.getSubject(token);
        UserDetails userDetails = loadUserByUsername(subject);
        String accessToken = accessTokenService.generateToken(userDetails);
        return new JwtResponse(accessToken, request.token(), "Bearer");
    }

    @SneakyThrows
    @Transactional
    public AuthUser register(UserRegisterDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        AuthUser authUser = authUserMapper.fromRegisterDTO(dto);
        authUserRepository.save(authUser);
        AuthUserDTO authUserDTO = authUserMapper.toDTO(authUser);
//        ActivationCode activationCode = activationCodeService.generateCode(authUserDTO);
        authUser.setStatus(AuthUser.Status.ACTIVE);
//        String link = basePath.formatted(activationCode.getActivationLink());
//        mailService.sendEmail(authUserDTO, link);
        return authUser;
    }

    @Transactional(noRollbackFor = GenericRuntimeException.class)
    public Boolean activateUser(String activationCode) {
        ActivationCode activationLink = activationCodeService.findByActivationLink(activationCode);
        if (activationLink.getValidTill().isBefore(LocalDateTime.now())) {
            activationCodeService.delete(activationLink.getId());
            throw new GenericRuntimeException("Activation Code is not active", 400);
        }

        AuthUser authUser = authUserRepository.findById(activationLink.getUserId()).orElseThrow(() -> {
            throw new GenericNotFoundException("User not found", 404);
        });

        authUser.setStatus(AuthUser.Status.ACTIVE);
        authUserRepository.save(authUser);
        return true;
    }

    public void signOut(String username) {
        authUserRepository.deleteByUsername(username);
    }

    @PreAuthorize("hasRole(T(uz.jl.task2_2.domains.sub.AuthRole).name.equals('OWNER'))")
    public void changePassword(PasswordDTO dto, String username) {
        AuthUser authUser = authUserRepository.findByUsername(username).
                orElseThrow(() -> new GenericNotFoundException("user not found", 404));

        if(!passwordEncoder.matches(dto.getOldPassword(), authUser.getPassword())){
            throw new GenericRuntimeException("password mismatch",401);
        }

        authUser.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        authUserRepository.save(authUser);
    }

    @PreAuthorize("hasRole(T(uz.jl.task2_2.domains.sub.AuthRole).name.equals('OWNER'))")
    public void sendMessage(MessageDTO dto) throws MessagingException {
        mailService.sendMessage(dto);
    }
}
