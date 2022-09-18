package uz.jl.task2_2.dtos.washer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasherUpdateDTO {

    @NotNull(message = "Washer id cannot be null in order to find particular washer")
    private Long id;

    @NotBlank(message = "Washer name cannot be null")
    private String name;

    @NotNull(message = "Washer phone number cannot be null")
    private int telephoneNumber;

    @NotNull(message = "Washer stake cannot be null")
    private int stake;

    @NotNull(message = "Washer activity status cannot be null")
    private Boolean isActive;

}
