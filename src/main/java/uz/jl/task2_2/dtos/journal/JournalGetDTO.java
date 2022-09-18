package uz.jl.task2_2.dtos.journal;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalGetDTO {

    private Integer orderId;

    private String username;

    private String previousCarModel;

    private String currentCarModel;


    private String previousCarNumber;

    private String currentCarNumber;

    private String previousServiceName;

    private String currentServiceName;

    private String previousServicePrice;

    private String currentServicePrice;

    private String previousPrice;


    private String currentPrice;

     private Boolean isCancelled;


    private String cancelledReason;


}
