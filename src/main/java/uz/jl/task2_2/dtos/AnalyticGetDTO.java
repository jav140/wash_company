package uz.jl.task2_2.dtos;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnalyticGetDTO {

    private int totalOrders;
    private int totalWashers;
    private int ordersSum;
    private BigDecimal washersSum;


}
