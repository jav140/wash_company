package uz.jl.task2_2.mappers;

import org.mapstruct.Mapper;
import uz.jl.task2_2.domains.Changes;
import uz.jl.task2_2.domains.Journal;
import uz.jl.task2_2.dtos.journal.JournalCreateDTO;
import uz.jl.task2_2.dtos.journal.JournalGetDTO;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ChangeMapper {
    Changes fromCreateDTO(JournalCreateDTO dto);

    List<JournalGetDTO> toGetDTOList(List<Journal> journalList);
}
