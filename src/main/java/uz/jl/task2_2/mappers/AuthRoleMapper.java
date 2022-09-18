package uz.jl.task2_2.mappers;

import org.mapstruct.Mapper;
import uz.jl.task2_2.domains.sub.AuthRole;
import uz.jl.task2_2.dtos.auth.AuthRoleCreateDTO;
import uz.jl.task2_2.dtos.auth.AuthRoleDTO;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AuthRoleMapper {
    AuthRoleDTO toDTO(AuthRole entity);

    List<AuthRoleDTO> toDTO(List<AuthRole> entities);

    AuthRole fromCreateDTO(AuthRoleCreateDTO dto);
}
