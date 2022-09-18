package uz.jl.task2_2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.domains.Washer;
import uz.jl.task2_2.dtos.service.ServiceCreateDTO;
import uz.jl.task2_2.dtos.service.ServiceGetDTO;
import uz.jl.task2_2.dtos.service.ServiceUpdateDTO;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.mappers.ServiceMapper;
import uz.jl.task2_2.repository.ServiceRepository;
import uz.jl.task2_2.repository.WashCompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceForService {

    private final ServiceRepository serviceRepository;
    private final WashCompanyRepository washCompanyRepository;

    private final ServiceMapper serviceMapper;

    public void createService(Long washCompanyId, ServiceCreateDTO dto) {

        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        uz.jl.task2_2.domains.Service service = serviceMapper.fromCreateDTO(dto);

        List<uz.jl.task2_2.domains.Service> services = washCompany.getServices();
        services.add(service);

        washCompany.setServices(services);

        serviceRepository.save(service);


    }

    public ServiceGetDTO getService(Long id) {

        uz.jl.task2_2.domains.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundException("Service not found", 404));

        return serviceMapper.toGetDTO(service);
    }

    public ServiceGetDTO updateService(Long washCompanyId, ServiceUpdateDTO dto) {

        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        serviceRepository.findById(dto.getId()).orElseThrow(() -> new GenericNotFoundException("service not found",404));

        uz.jl.task2_2.domains.Service service = serviceMapper.fromUpdateDTO(dto);

        serviceRepository.save(service);

        return serviceMapper.toGetDTO(service);
    }

    public List<ServiceGetDTO> getWashersByName(Long washCompanyId, String searchName, int page) {

         washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        Pageable pageable = PageRequest.of(page, 10);
        Page<uz.jl.task2_2.domains.Service> services = null;
        if(searchName.equals("")){
            services = serviceRepository.findAll(pageable);
        } else {
            services =  serviceRepository.findByNameContainingIgnoreCase(searchName,pageable,washCompanyId);
        }
        List<uz.jl.task2_2.domains.Service> serviceList = services.getContent();

        return serviceMapper.toGetDTOList(serviceList);
    }
}
