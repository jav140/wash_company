package uz.jl.task2_2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.domains.Washer;
import uz.jl.task2_2.dtos.washer.WasherCreateDTO;
import uz.jl.task2_2.dtos.washer.WasherGetDTO;
import uz.jl.task2_2.dtos.washer.WasherUpdateDTO;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.mappers.WasherMapper;
import uz.jl.task2_2.repository.WashCompanyRepository;
import uz.jl.task2_2.repository.WasherRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WasherService {

    private final WasherRepository washerRepository;
    private final WashCompanyRepository washCompanyRepository;
    private final WasherMapper washerMapper;
    public void createWasher(Long washCompanyId, WasherCreateDTO dto) {
        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        Washer washer = washerMapper.fromCreateDTO(dto);
        washCompany.getWashers().add(washer);
        washCompanyRepository.save(washCompany);
        washerRepository.save(washer);


    }

    public WasherGetDTO updateWasher(Long washCompanyId, WasherUpdateDTO dto) {
         washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

       washerRepository.findById(dto.getId())
                .orElseThrow(() -> new GenericNotFoundException("Washer not found", 404));

        Washer washer = washerMapper.fromUpdateDTO(dto);

        washerRepository.save(washer);

        return washerMapper.toGetDTO(washer);

    }

    public WasherGetDTO getWasher(Long washerId) {
        Washer washer = washerRepository.findById(washerId)
                .orElseThrow(() -> new GenericNotFoundException("Washer not found", 404));

        return washerMapper.toGetDTO(washer);
    }

    public List<WasherGetDTO> getWashersByName(Long washCompanyId, String name, int page) {

        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        Pageable pageable = PageRequest.of(page, 10);
        Page<Washer> washerList = null;
        if(name.equals("")){
            washerList = washerRepository.findAll(pageable);
        } else {
           washerList =  washerRepository.findByNameContainingIgnoreCase(name,pageable,washCompany.getId());
        }


        List<Washer> washers = washerList.getContent();

        return washerMapper.toListGetDTO(washers);
    }
}
