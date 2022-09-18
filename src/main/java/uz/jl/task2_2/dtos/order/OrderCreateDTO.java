package uz.jl.task2_2.dtos.order;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreateDTO {
    @NotNull(message = "price cannot be null")
    private int price;

    @NotBlank(message = "car model cannot be null")
    private String carModel;

    @NotBlank(message = "car number cannot be null")
    private String carNumber;

    @NotBlank(message = "client name cannot be null")
    private String clientName;

    @NotNull(message = "client number cannot be null")
    private int clientNumber;
}
