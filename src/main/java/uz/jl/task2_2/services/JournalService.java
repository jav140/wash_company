package uz.jl.task2_2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.domains.Changes;
import uz.jl.task2_2.domains.Journal;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.dtos.journal.JournalCreateDTO;
import uz.jl.task2_2.dtos.journal.JournalGetDTO;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.mappers.ChangeMapper;
import uz.jl.task2_2.repository.JournalRepository;
import uz.jl.task2_2.repository.WashCompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {

    private final WashCompanyRepository washCompanyRepository;
    private final JournalRepository journalRepository;

    private final ChangeMapper changeMapper;
    public void createJournal(Long washCompanyId, JournalCreateDTO dto) {
        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        Changes change = changeMapper.fromCreateDTO(dto);

        Journal journal = Journal.builder()
                .changes(change)
                .build();

        washCompany.getJournals().add(journal);

        washCompanyRepository.save(washCompany);

        journalRepository.save(journal);


    }

    public List<JournalGetDTO> getJournals(Long washCompanyId, int page) {

        washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        Pageable pageable = PageRequest.of(page, 10);

        Page<Journal> journals = null;

        journals = journalRepository.findByCompanyId(washCompanyId,pageable);
        List<Journal> journalList = journals.getContent();

        return changeMapper.toGetDTOList(journalList);
    }
}
