package uz.jl.task2_2.dtos.service;

import lombok.*;
import uz.jl.task2_2.domains.OrderTable;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceGetDTO {


    private Long id;

    private String name;

    private int duration;

    private int price;




}
