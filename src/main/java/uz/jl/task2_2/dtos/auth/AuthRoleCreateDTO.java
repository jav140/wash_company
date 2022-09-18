package uz.jl.task2_2.dtos.auth;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AuthRoleCreateDTO {
    private final String code;
    private final String name;
}
