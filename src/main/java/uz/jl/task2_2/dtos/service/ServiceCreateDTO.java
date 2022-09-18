package uz.jl.task2_2.dtos.service;


import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceCreateDTO {

    @NotBlank(message = "Service name cannot be null")
    private String name;

    @NotNull(message = "Service duration cannot be null")
    private int duration;

    @NotNull(message = "Service price cannot be null")
    private int price;


}
