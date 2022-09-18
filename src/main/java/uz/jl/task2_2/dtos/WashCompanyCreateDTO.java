package uz.jl.task2_2.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WashCompanyCreateDTO {

    @NotBlank(message = "company name cannot be blank")
    private String name;

    @NotBlank(message = "location name cannot be blank")
    private String location;

}
