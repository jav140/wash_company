package uz.jl.task2_2.controller;

import org.springframework.web.bind.annotation.*;
import uz.jl.task2_2.dtos.order.OrderCreateDTO;
import uz.jl.task2_2.dtos.order.OrderGetDTO;
import uz.jl.task2_2.dtos.order.OrderUpdateDTO;
import uz.jl.task2_2.dtos.service.ServiceCreateDTO;
import uz.jl.task2_2.dtos.service.ServiceGetDTO;
import uz.jl.task2_2.dtos.service.ServiceUpdateDTO;
import uz.jl.task2_2.dtos.washer.WasherGetDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.ServiceForService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ServiceController extends ApiController<ServiceForService>{

    protected ServiceController(ServiceForService service) {
        super(service);
    }

    @PostMapping(PATH + "/{washCompanyId}/insertService")
    public ApiResponse<Void> createService(@PathVariable Long washCompanyId, @Valid @RequestBody ServiceCreateDTO dto) {
        service.createService(washCompanyId, dto);
        return new ApiResponse<>(200);
    }

    @GetMapping(PATH + "/service/{id}")
    public ApiResponse<ServiceGetDTO> getService(@PathVariable Long id) {
        ServiceGetDTO serviceGetDTO = service.getService(id);
        return new ApiResponse<>(serviceGetDTO, 200);
    }

    @PostMapping(PATH + "/{washCompanyId}/updateService")
    public ApiResponse<ServiceGetDTO> update(@PathVariable Long washCompanyId, @Valid @RequestBody ServiceUpdateDTO dto) {
       ServiceGetDTO serviceGetDTO =  service.updateService(washCompanyId,dto);
        return new ApiResponse<>(serviceGetDTO,200);
    }

    @GetMapping(PATH + "/{washCompanyId}/services/{searchName}/{page}")
    public ApiResponse<List<ServiceGetDTO>> getWashersByName(@PathVariable("washCompanyId") Long washCompanyId,
                                                            @PathVariable("searchName") String searchName,
                                                            @PathVariable("page") int page) {
        List<ServiceGetDTO> serviceGetDTOS = service.getWashersByName(washCompanyId,searchName,page);
        return new ApiResponse<>(serviceGetDTOS,200);
    }

}
