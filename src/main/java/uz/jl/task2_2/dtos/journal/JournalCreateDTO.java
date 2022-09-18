package uz.jl.task2_2.dtos.journal;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalCreateDTO {

    @NotNull(message = "order id cannot be null")
    private Integer orderId;

    @NotBlank(message = "username cannot be null")
    private String username;

    @NotBlank(message = "previous car model cannot be null")
    private String previousCarModel;

    @NotBlank(message = "current car model cannot be null")
    private String currentCarModel;

    @NotBlank(message = "previous car number cannot be null")
    private String previousCarNumber;

    @NotBlank(message = "current car number cannot be null")
    private String currentCarNumber;

    @NotBlank(message = "previous service name cannot be null")
    private String previousServiceName;

    @NotBlank(message = "current service name cannot be null")
    private String currentServiceName;

    @NotBlank(message = "previous service price cannot be null")
    private String previousServicePrice;

    @NotBlank(message = "current service price cannot be null")
    private String currentServicePrice;

    @NotBlank(message = "previous price cannot be null")
    private String previousPrice;

    @NotBlank(message = "current price cannot be null")
    private String currentPrice;

    @NotBlank(message = " isCancelled cannot be null")
    private Boolean isCancelled;

    @NotBlank(message = "cancelled reason number cannot be null")
    private String cancelledReason;

}
