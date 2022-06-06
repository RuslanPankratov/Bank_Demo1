package bank.controller.service;


import bank.core.service.calculator.InsuranceCalculatorService;
import bank.core.service.calculator.InsurancePayService;

import bank.dto.insurance.calculator.InsuranceCalculatorRequest;
import bank.dto.insurance.calculator.InsuranceCalculatorTransactionResponse;
import bank.dto.insurance.pay.InsurancePayRequest;
import bank.dto.insurance.pay.InsurancePayTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


@Slf4j
@RestController
@AllArgsConstructor
public class InsuranceCalculatorController {

    private InsuranceCalculatorService insuranceCalculator;
    private InsurancePayService insurancePayService;

    @PutMapping("/insuranceCalculate")
    public Optional<InsuranceCalculatorTransactionResponse> calculate(
            @RequestBody @Valid InsuranceCalculatorRequest request) {
        log.debug("Received Insurance Calculator request: {}", request);
        return insuranceCalculator.calculate(request);
    }

    @PutMapping("/insurancePay")
    public Optional<InsurancePayTransactionResponse> pay(@RequestBody @Valid InsurancePayRequest request) {
        log.debug("Received Insurance Pay request: {}", request);
        return insurancePayService.pay(request.getIdUser());
    }

}
