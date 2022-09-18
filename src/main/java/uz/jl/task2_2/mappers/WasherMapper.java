package uz.jl.task2_2.mappers;

import org.mapstruct.Mapper;
import uz.jl.task2_2.domains.Washer;
import uz.jl.task2_2.dtos.washer.WasherCreateDTO;
import uz.jl.task2_2.dtos.washer.WasherGetDTO;
import uz.jl.task2_2.dtos.washer.WasherUpdateDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WasherMapper {

    Washer fromCreateDTO(WasherCreateDTO dto);

    Washer fromUpdateDTO(WasherUpdateDTO dto);

    WasherGetDTO toGetDTO(Washer washer);

    List<WasherGetDTO> toListGetDTO(List<Washer> washers);
}
