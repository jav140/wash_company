package uz.jl.task2_2.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.jl.task2_2.config.security.UserDetails;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.dtos.LoginRequest;
import uz.jl.task2_2.dtos.WashCompanyCreateDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.WashCompanyService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WashCompanyController extends ApiController<WashCompanyService> {


    protected WashCompanyController(WashCompanyService service) {
        super(service);
    }

    @PostMapping(PATH + "/washCompanyInsert")
    public ApiResponse<Void> create(@Valid @RequestBody WashCompanyCreateDTO dto, @AuthenticationPrincipal UserDetails userDetails) {
        service.createWashCompany(dto, userDetails.getUsername());
        return new ApiResponse<>(200);
    }

    @PostMapping(PATH + "/getWashCompanyID")
    public ApiResponse<List<Long>> getWashCompanyId(@RequestBody LoginRequest loginRequest) {
        List<Long> ids = service.getAllWashCompanyId(loginRequest);
        return new ApiResponse<>(ids);
    }





}
