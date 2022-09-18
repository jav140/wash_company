package uz.jl.task2_2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.domains.AuthUser;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.dtos.LoginRequest;
import uz.jl.task2_2.dtos.WashCompanyCreateDTO;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.mappers.WashCompanyMapper;
import uz.jl.task2_2.repository.AuthUserRepository;
import uz.jl.task2_2.repository.WashCompanyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WashCompanyService {

    private final WashCompanyRepository washCompanyRepository;
    private final AuthUserRepository authUserRepository;

    private final PasswordEncoder passwordEncoder;


    private final WashCompanyMapper mapper;
    public void createWashCompany(WashCompanyCreateDTO dto, String username) {
        WashCompany washCompany = mapper.fromCreateDto(dto);

//        WashCompany washCompany = WashCompany.builder()
//                .location(dto.getLocation())
//                .name(dto.getName())
//                .build();

        AuthUser user = authUserRepository.findByUsername(username).orElseThrow(() -> new GenericNotFoundException("User not found", 401));
        ArrayList<AuthUser> users = new ArrayList<>();
        users.add(user);
        washCompany.setUsers(users);
        washCompanyRepository.save(washCompany);
    }

    public List<Long> getAllWashCompanyId(LoginRequest loginRequest) {
        AuthUser user = authUserRepository.findByUsername(loginRequest.username()).orElseThrow(() -> new GenericNotFoundException("User not found", 401));
        if(!passwordEncoder.matches(loginRequest.password(),user.getPassword())){
            throw new BadCredentialsException("Password mismatch");
        }
        return washCompanyRepository.findAllWashCompaniesByAuthUserId(user.getId());
    }
}
