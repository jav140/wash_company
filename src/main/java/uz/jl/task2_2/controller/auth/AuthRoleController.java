package uz.jl.task2_2.controller.auth;

import org.springframework.web.bind.annotation.*;
import uz.jl.task2_2.controller.ApiController;
import uz.jl.task2_2.dtos.auth.AuthRoleCreateDTO;
import uz.jl.task2_2.dtos.auth.AuthRoleDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.auth.AuthRoleService;

import javax.validation.Valid;
import java.util.List;


@RestController
public class AuthRoleController extends ApiController<AuthRoleService> {


    protected AuthRoleController(AuthRoleService service) {
        super(service);
    }

    @GetMapping(PATH + "/role")
    public ApiResponse<List<AuthRoleDTO>> getAll() {
        return new ApiResponse<>(service.getAll());
    }

    @GetMapping(PATH + "/role/{id}")
    public ApiResponse<AuthRoleDTO> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @PostMapping(PATH + "/role")
    public ApiResponse<Void> create(@Valid @RequestBody AuthRoleCreateDTO dto) {
        service.create(dto);
        // TODO: 19/08/22 standardize status codes
        return new ApiResponse<>(201);
    }

    @DeleteMapping(PATH + "/role/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        // TODO: 19/08/22 standardize status codes
        return new ApiResponse<>(204);
    }


}
