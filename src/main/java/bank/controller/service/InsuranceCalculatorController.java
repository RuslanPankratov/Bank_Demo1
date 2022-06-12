package bank.controller.service;


import bank.core.service.calculator.InsuranceCalculatorService;
import bank.core.service.calculator.InsurancePayService;

import bank.core.service.credit.dto.insurance.calculator.InsuranceCalculatorRequest;
import bank.core.service.credit.dto.insurance.calculator.InsuranceCalculatorTransactionResponse;
import bank.core.service.credit.dto.insurance.pay.InsurancePayTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class InsuranceCalculatorController {


    private InsuranceCalculatorService insuranceCalculator;
    private InsurancePayService insurancePayService;

    @PutMapping("{id}/insurances/operation=CALCULATE")
    public Optional<InsuranceCalculatorTransactionResponse> calculate(
            @PathVariable("id") Integer idUser,
            @RequestBody @Valid InsuranceCalculatorRequest request) {

        log.debug("Received Insurance Calculator request: {}", request);
        log.debug("Id user: {}", idUser);

        return insuranceCalculator.calculate(request, idUser);
    }

    @PutMapping("{id}/insurance/operation=PAY")
    public Optional<InsurancePayTransactionResponse> pay(@PathVariable("id") Integer idUser) {
        log.debug("Received Insurance Pay request: {}", idUser);
        return insurancePayService.pay(idUser);
    }

}
