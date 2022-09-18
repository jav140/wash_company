package uz.jl.task2_2.dtos.auth;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordDTO {

    @NotBlank(message = "old password cannot be null")
    private String oldPassword;

    @NotBlank(message = "new password cannot be null")
    private String newPassword;

}
