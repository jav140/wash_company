package uz.jl.task2_2.dtos.washer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasherGetDTO {


    private Long id;

    private String name;

    private int telephoneNumber;

    private int stake;

    private Boolean isActive;

}
