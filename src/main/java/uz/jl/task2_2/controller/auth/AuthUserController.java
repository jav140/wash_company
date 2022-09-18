package uz.jl.task2_2.controller.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.task2_2.config.security.UserDetails;
import uz.jl.task2_2.controller.ApiController;
import uz.jl.task2_2.domains.AuthUser;
import uz.jl.task2_2.dtos.*;
import uz.jl.task2_2.dtos.auth.PasswordDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.AuthUserService;

import javax.mail.MessagingException;
import javax.validation.Valid;




@RestController
public class AuthUserController extends ApiController<AuthUserService> {
    protected AuthUserController(AuthUserService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/auth/login", produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ApiResponse<>(service.login(loginRequest));
    }

    @GetMapping(value = PATH + "/auth/refresh", produces = "application/json")
    public ApiResponse<JwtResponse> login(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return new ApiResponse<>(service.refreshToken(refreshTokenRequest));
    }

    @PostMapping(PATH + "/auth/register")
    public ApiResponse<AuthUser> register(@Valid @RequestBody UserRegisterDTO dto) {
        return new ApiResponse<>(service.register(dto));
    }

    @GetMapping(value = PATH + "/auth/signOut")
    public ApiResponse<Void> signOut(@AuthenticationPrincipal UserDetails userDetails) {
        service.signOut(userDetails.getUsername());
        return new ApiResponse<>();
    }


    @GetMapping(PATH + "/auth/activate")
    public ApiResponse<Boolean> register(@RequestParam(name = "activation_code") String activationCode) {
        return new ApiResponse<>(service.activateUser(activationCode));
    }

    @GetMapping(PATH + "/auth/me")
    public AuthUser me() {
        return null;
    }



    @PostMapping(PATH + "/auth/changePassword")
    public ApiResponse<Void> changePassword(@Valid @RequestBody PasswordDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        service.changePassword(dto,userDetails.getUsername());
        return new ApiResponse<>(200);
    }


    @PostMapping(PATH + "/auth/sendMessage")
    public ApiResponse<String> sendMessage(@Valid @RequestBody MessageDTO dto) throws MessagingException {
       service.sendMessage(dto);
       return new ApiResponse<>("Message sent!",200);
    }


}
