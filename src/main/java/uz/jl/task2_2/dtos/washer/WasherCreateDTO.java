package uz.jl.task2_2.dtos.washer;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasherCreateDTO {

    @NotBlank(message = "Washer name cannot be null")
    private String name;

    @NotNull(message = "Washer phone number cannot be null")
    private int telephoneNumber;

    @NotNull(message = "Washer stake cannot be null")
    private int stake;

    private Boolean isActive;
}
