package uz.jl.task2_2.controller;

import lombok.*;
import org.springframework.web.bind.annotation.*;
import uz.jl.task2_2.dtos.order.OrderCreateDTO;
import uz.jl.task2_2.dtos.washer.WasherCreateDTO;
import uz.jl.task2_2.dtos.washer.WasherGetDTO;
import uz.jl.task2_2.dtos.washer.WasherUpdateDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.WasherService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WasherController extends ApiController<WasherService> {


    protected WasherController(WasherService service) {
        super(service);
    }


    @PostMapping(PATH + "/{washCompanyId}/insertWasher")
    public ApiResponse<Void> createWasher(@PathVariable("washCompanyId")Long washCompanyId,@Valid @RequestBody WasherCreateDTO dto) {
        service.createWasher(washCompanyId, dto);
        return new ApiResponse<>(200);
    }

    @PostMapping(PATH + "/{washCompanyId}/updateWasher")
    public ApiResponse<WasherGetDTO> updateWasher(@PathVariable("washCompanyId")Long washCompanyId, @Valid @RequestBody WasherUpdateDTO dto) {
        WasherGetDTO washerGetDTO = service.updateWasher(washCompanyId, dto);
        return new ApiResponse<>(washerGetDTO,200);
    }

    @GetMapping(PATH + "/washer/{id}")
    public ApiResponse<WasherGetDTO> getWasher(@PathVariable("id") Long washerId) {
        WasherGetDTO washerGetDTO = service.getWasher(washerId);
        return new ApiResponse<>(washerGetDTO,200);
    }

    @GetMapping(PATH + "/{washCompanyId}/washers/{searchName}/{page}")
    public ApiResponse<List<WasherGetDTO>> getWashersByName(@PathVariable("washCompanyId") Long washCompanyId,
                                                            @PathVariable("searchName") String searchName,
                                                            @PathVariable("page") int page) {
        List<WasherGetDTO> washerGetDTOs = service.getWashersByName(washCompanyId,searchName,page);
        return new ApiResponse<>(washerGetDTOs,200);
    }


}
