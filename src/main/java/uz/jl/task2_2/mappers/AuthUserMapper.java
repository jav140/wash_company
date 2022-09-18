package uz.jl.task2_2.mappers;

import org.mapstruct.Mapper;
import uz.jl.task2_2.domains.AuthUser;
import uz.jl.task2_2.dtos.UserRegisterDTO;
import uz.jl.task2_2.dtos.auth.AuthUserDTO;



@Mapper(componentModel = "spring")
public interface AuthUserMapper {
    AuthUser fromRegisterDTO(UserRegisterDTO dto);

    AuthUserDTO toDTO(AuthUser domain);
}
