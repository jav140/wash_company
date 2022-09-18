package uz.jl.task2_2.mappers;

import org.mapstruct.Mapper;
import uz.jl.task2_2.domains.OrderTable;
import uz.jl.task2_2.dtos.order.OrderCreateDTO;
import uz.jl.task2_2.dtos.order.OrderGetDTO;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderTable fromCreateDTO(OrderCreateDTO dto);

    OrderGetDTO toGetDTO(OrderTable order);
}
