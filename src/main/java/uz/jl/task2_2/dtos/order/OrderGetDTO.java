package uz.jl.task2_2.dtos.order;

import lombok.*;
import uz.jl.task2_2.domains.Service;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.domains.Washer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderGetDTO {


    private Long id;


    private int price;


    private String carModel;


    private String carNumber;


    private String clientName;


    private int clientNumber;


    private Boolean isActive;


    private Boolean isCancelled;

    private LocalDateTime date = LocalDateTime.now();

    private List<WashCompany> washCompany;

    private Set<Washer> washers;

    private Set<Service> services;

}
