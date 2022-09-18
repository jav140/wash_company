package uz.jl.task2_2.mappers;

import org.mapstruct.Mapper;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.dtos.WashCompanyCreateDTO;

@Mapper(componentModel = "Spring")
public interface WashCompanyMapper {

    WashCompany fromCreateDto(WashCompanyCreateDTO dto);
}
