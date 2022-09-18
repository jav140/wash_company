package uz.jl.task2_2.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.jl.task2_2.config.security.UserDetails;
import uz.jl.task2_2.dtos.WashCompanyCreateDTO;
import uz.jl.task2_2.dtos.order.OrderCreateDTO;
import uz.jl.task2_2.dtos.order.OrderGetDTO;
import uz.jl.task2_2.dtos.order.OrderUpdateDTO;
import uz.jl.task2_2.dtos.order.PaginatedOrderResponseDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.OrderService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class OrderController extends ApiController<OrderService> {


    protected OrderController(OrderService service) {
        super(service);
    }

    @PostMapping(PATH + "/{washCompanyId}/insertOrder")
    public ApiResponse<Void> createOrder(@PathVariable Long washCompanyId, @Valid @RequestBody OrderCreateDTO dto) {
        service.createOrder(washCompanyId, dto);
        return new ApiResponse<>(200);
    }

    @GetMapping(PATH + "/order?{id}")
    public ApiResponse<OrderGetDTO> getOrder(@PathVariable Long id) {
        OrderGetDTO orderGetDTO = service.getOrder(id);
        return new ApiResponse<>(orderGetDTO, 200);
    }

    @PostMapping(PATH + "/{washCompanyId}/updateOrder/{orderId}")
    public ApiResponse<Void> update(@PathVariable Long washCompanyId, @PathVariable Long orderId, @Valid @RequestBody OrderUpdateDTO dto) {
        service.updateOrder(washCompanyId, orderId, dto);
        return new ApiResponse<>(200);
    }


    @GetMapping(PATH + "/{washCompanyId}/orders/{isActive}/{dateFrom}/{dateTo}/{page}")
    public ApiResponse<PaginatedOrderResponseDTO> getInfo(@PathVariable Long washCompanyId, @PathVariable Boolean isActive,
                                                          @PathVariable String dateFrom, @PathVariable String dateTo,
                                                          @PathVariable int page) {
        PaginatedOrderResponseDTO completedOrActiveOrders = service.getCompletedOrActiveOrders(washCompanyId, isActive, dateFrom, dateTo, page);

        return new ApiResponse<>(completedOrActiveOrders, 200);

    }


}
