package bank.controller.admin;

import bank.core.service.insurance.AddInsuranceService;
import bank.core.service.insurance.FindAllInsuranceService;
import bank.core.service.insurance.GetInsuranceByIdService;
import bank.core.service.insurance.UpdateInsuranceService;
import bank.core.service.credit.dto.insurance.add.AddInsuranceRequest;
import bank.core.service.credit.dto.insurance.add.AddInsuranceResponse;
import bank.core.service.credit.dto.insurance.find.FindAllInsuranceResponse;
import bank.core.service.credit.dto.insurance.find.GetByIdInsuranceResponse;
import bank.core.service.credit.dto.insurance.update.UpdateInsuranceRequest;
import bank.core.service.credit.dto.insurance.update.UpdateInsuranceResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/insurances")
@AllArgsConstructor
public class InsuranceController {


    private FindAllInsuranceService findAllInsuranceService;
    private GetInsuranceByIdService getInsuranceByIdService;
    private AddInsuranceService addInsuranceService;
    private UpdateInsuranceService updateInsuranceService;

    @GetMapping()
    public FindAllInsuranceResponse findAllUsers() {
        log.debug("Find all credit card received");
        return findAllInsuranceService.findAll();
    }

    @GetMapping("/{id}")
    public GetByIdInsuranceResponse findById(@PathVariable("id") Integer id) {
        log.debug("Find by id request received, id: {}", id);
        return getInsuranceByIdService.getInsuranceById(id);
    }

    @PostMapping()
    public AddInsuranceResponse create(@RequestBody @Valid AddInsuranceRequest request) {
        log.debug("Add Insurance Request request: {}", request);
        return addInsuranceService.add(request);
    }

    @PutMapping()
    public UpdateInsuranceResponse update(@RequestBody @Valid UpdateInsuranceRequest request) {
        log.debug("Received update Insurance request: {}", request);
        return updateInsuranceService.update(request);
    }

}
