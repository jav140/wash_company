package uz.jl.task2_2.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.jl.task2_2.dtos.journal.JournalCreateDTO;
import uz.jl.task2_2.dtos.journal.JournalGetDTO;
import uz.jl.task2_2.dtos.order.OrderCreateDTO;
import uz.jl.task2_2.dtos.washer.WasherGetDTO;
import uz.jl.task2_2.response.ApiResponse;
import uz.jl.task2_2.services.JournalService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class JournalController extends ApiController<JournalService> {

    protected JournalController(JournalService service) {
        super(service);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping(PATH + "/{washCompanyId}/insertJournal")
    public ApiResponse<Void> createOrder(@PathVariable Long washCompanyId, @Valid @RequestBody JournalCreateDTO dto) {
        service.createJournal(washCompanyId, dto);

        return new ApiResponse<>(200);
    }

    @GetMapping(PATH + "/{washCompanyId}/getJournals/{page}")
    public ApiResponse<List<JournalGetDTO>> getWashersByName(@PathVariable("washCompanyId") Long washCompanyId,
                                                             @PathVariable("page") int page) {
        List<JournalGetDTO> journalGetDTOS = service.getJournals(washCompanyId,page);
        return new ApiResponse<>(journalGetDTOS,200);
    }
}
