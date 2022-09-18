package uz.jl.task2_2.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.jl.task2_2.dtos.AnalyticGetDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.AnalyticsService;

@RestController
public class AnalyticsController extends ApiController<AnalyticsService> {

    protected AnalyticsController(AnalyticsService service) {
        super(service);
    }

    @GetMapping(PATH + "/{washCompanyId}/analytics/{dateFrom}/{dateTo}")
    public ApiResponse<AnalyticGetDTO> getAnalytics(@PathVariable("washCompanyId") Long washCompanyId,
                                                    @PathVariable("dateFrom") String dateFrom,
                                                    @PathVariable("dateTo") String dateTo){

        AnalyticGetDTO analyticGetDTO = service.getInfo(washCompanyId,dateFrom,dateTo);

        return new ApiResponse<>(analyticGetDTO,200);

    }
}
