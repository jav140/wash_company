package uz.jl.task2_2.mappers;

import org.mapstruct.Mapper;
import uz.jl.task2_2.domains.Service;
import uz.jl.task2_2.dtos.service.ServiceCreateDTO;
import uz.jl.task2_2.dtos.service.ServiceGetDTO;
import uz.jl.task2_2.dtos.service.ServiceUpdateDTO;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ServiceMapper {

    Service fromCreateDTO(ServiceCreateDTO dto);

    ServiceGetDTO toGetDTO(Service service);

    Service fromUpdateDTO(ServiceUpdateDTO dto);

    List<ServiceGetDTO> toGetDTOList(List<Service> serviceList);
}
