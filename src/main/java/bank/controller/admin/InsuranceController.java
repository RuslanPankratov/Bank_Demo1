package bank.controller.admin;

import bank.core.service.insurance.AddInsuranceService;
import bank.core.service.insurance.FindAllInsuranceService;
import bank.core.service.insurance.GetInsuranceByIdService;
import bank.core.service.insurance.UpdateInsuranceService;
import bank.dto.insurance.add.AddInsuranceRequest;
import bank.dto.insurance.add.AddInsuranceResponse;
import bank.dto.insurance.find.FindAllInsuranceResponse;
import bank.dto.insurance.find.GetByIdInsuranceResponse;
import bank.dto.insurance.update.UpdateInsuranceRequest;
import bank.dto.insurance.update.UpdateInsuranceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@AllArgsConstructor
public class InsuranceController {


    private FindAllInsuranceService findAllInsuranceService;
    private GetInsuranceByIdService getInsuranceByIdService;
    private AddInsuranceService addInsuranceService;
    private UpdateInsuranceService updateInsuranceService;

    @GetMapping("/insurance")
    public FindAllInsuranceResponse findAllUsers() {
        log.debug("Find all credit card received");
        return findAllInsuranceService.findAll();
    }

    @GetMapping("/insurance/{id}")
    public GetByIdInsuranceResponse findById(@PathVariable("id") Integer id) {
        log.debug("Find by id request received, id: {}", id);
        return getInsuranceByIdService.getInsuranceById(id);
    }

    @PostMapping("/insurance")
    public AddInsuranceResponse create(@RequestBody @Valid AddInsuranceRequest request) {
        log.debug("Add Insurance Request request: {}", request);
        return addInsuranceService.add(request);
    }

    @PutMapping("/insurance")
    public UpdateInsuranceResponse update(@RequestBody @Valid UpdateInsuranceRequest request) {
        log.debug("Received update Insurance request: {}", request);
        return updateInsuranceService.update(request);
    }

}
