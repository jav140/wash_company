package uz.jl.task2_2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.domains.OrderTable;
import uz.jl.task2_2.domains.WashCompany;
import uz.jl.task2_2.domains.Washer;
import uz.jl.task2_2.dtos.order.OrderCreateDTO;
import uz.jl.task2_2.dtos.order.OrderGetDTO;
import uz.jl.task2_2.dtos.order.OrderUpdateDTO;
import uz.jl.task2_2.dtos.order.PaginatedOrderResponseDTO;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.mappers.OrderMapper;
import uz.jl.task2_2.repository.OrderRepository;
import uz.jl.task2_2.repository.ServiceRepository;
import uz.jl.task2_2.repository.WashCompanyRepository;
import uz.jl.task2_2.repository.WasherRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WashCompanyRepository washCompanyRepository;
    private final WasherRepository washerRepository;
    private final ServiceRepository serviceRepository;
    private final OrderMapper orderMapper;


    public void createOrder(Long washCompanyId, OrderCreateDTO dto) {
        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        OrderTable order = orderMapper.fromCreateDTO(dto);
         washCompany.getOrders().add(order);
        washCompanyRepository.save(washCompany);
        orderRepository.save(order);

    }

    public OrderGetDTO getOrder(Long orderId) {
        OrderTable order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GenericNotFoundException("Order not found", 404));
        List<WashCompany> washCompanies = washCompanyRepository.findByOrderId(orderId);
        Set<uz.jl.task2_2.domains.Service> services = serviceRepository.findByOrderId(orderId);
        Set<Washer> washers = washerRepository.findByOrderId(orderId);
        OrderGetDTO orderGetDTO = orderMapper.toGetDTO(order);
        orderGetDTO.setServices(services);
        orderGetDTO.setWashers(washers);
        orderGetDTO.setWashCompany(washCompanies);

        return orderGetDTO;
    }

    public void updateOrder(Long washCompanyId, Long orderId, OrderUpdateDTO dto) {

        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));

        OrderTable order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GenericNotFoundException("Order not found", 404));

        order.setCarModel(dto.getCarModel());
        order.setCarNumber(dto.getCarNumber());
        order.setPrice(dto.getPrice());
        order.setIsActive(dto.getIsActive());
        order.setIsCancelled(dto.getIsCancelled());

        orderRepository.save(order);

        Set<uz.jl.task2_2.domains.Service> services = dto.getServices();
        for (uz.jl.task2_2.domains.Service service : services) {
            service.setOrder(order);
            serviceRepository.save(service);
        }


    }

    public PaginatedOrderResponseDTO getCompletedOrActiveOrders(Long washCompanyId, Boolean isActive, String dateFrom,
                                                                String dateTo, int pageNumber) {

        WashCompany washCompany = washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found", 404));
        List<OrderTable> orders = washCompanyRepository.findOrdersById(washCompanyId, isActive, LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));

//        ArrayList<OrderTable> orderTables = new ArrayList<>();
//
//        for (OrderTable order : orders) {
//            if (order.getDate().isAfter(dateFrom) && order.getDate().isBefore(dateTo)) {
//                orderTables.add(order);
//            }
//        }

        Page<OrderTable> page = new PageImpl<>(orders.subList((pageNumber - 1) * 10, pageNumber * 10));
        return PaginatedOrderResponseDTO.builder()
                .numberOfItems(page.getTotalElements()).numberOfPages(page.getTotalPages())
                .orderTableList(page.getContent())
                .numberOfPages(10)
                .build();


//        public PaginatedBookResponse readBooks(Pageable pageable) {
//            Page<Book> books = bookRepository.findAll(pageable);
//            return PaginatedBookResponse.builder()
//                    .numberOfItems(books.getTotalElements()).numberOfPages(books.getTotalPages())
//                    .bookList(books.getContent())
//                    .build();
//        }

//
//        Page<OrderTable> orders = washCompanyRepository.findAllById(washCompanyId, page);
//
//        for (OrderTable order : orders) {
//            if(order.getIsActive().equals(isActive) && order.getDate().isAfter(dateFrom) && order.getDate().isBefore(dateTo)){
//
//            }
//        }


    }
}
