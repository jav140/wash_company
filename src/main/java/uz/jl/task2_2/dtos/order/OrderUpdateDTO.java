package uz.jl.task2_2.dtos.order;

import lombok.*;
import org.mapstruct.ap.internal.util.Services;
import uz.jl.task2_2.domains.AuthUser;
import uz.jl.task2_2.domains.Service;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUpdateDTO {

    private int price;

    private String carModel;


    private String carNumber;

    private Boolean isActive;

    private Boolean isCancelled;

    private Set<AuthUser> admins;

    private Set<Service> services;


}
