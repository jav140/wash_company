package uz.jl.task2_2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jl.task2_2.domains.OrderTable;
import uz.jl.task2_2.domains.Washer;
import uz.jl.task2_2.dtos.AnalyticGetDTO;
import uz.jl.task2_2.exceptions.GenericNotFoundException;
import uz.jl.task2_2.repository.WashCompanyRepository;
import uz.jl.task2_2.repository.WasherRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final WashCompanyRepository washCompanyRepository;
    private final WasherRepository washerRepository;
    public AnalyticGetDTO getInfo(Long washCompanyId, String dateFrom, String dateTo) {

        washCompanyRepository.findById(washCompanyId)
                .orElseThrow(() -> new GenericNotFoundException("Wash company not found",404));

      List<OrderTable> orders = washCompanyRepository.getValidOrders(washCompanyId,LocalDateTime.parse(dateFrom),LocalDateTime.parse(dateTo));
      int sum = 0;
      for (OrderTable order : orders) {
            sum+= order.getPrice();
        }

        List<Washer> washers = washerRepository.getValidOrders(washCompanyId,LocalDateTime.parse(dateFrom),LocalDateTime.parse(dateTo));

      double washerSum = 0;

        for (Washer washer : washers) {
            washerSum+=washers.size()*0.01*washer.getOrder().getPrice();
        }

        return AnalyticGetDTO.builder()
              .totalOrders(orders.size())
              .ordersSum(sum)
              .totalWashers(washers.size())
              .washersSum(BigDecimal.valueOf(washerSum))
              .build();

    }
}
