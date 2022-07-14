package bank.controller;


import bank.core.service.calculator.InsuranceCalculatorService;
import bank.core.service.calculator.InsurancePayService;

import bank.dto.insurance.calculator.InsuranceCalculatorRequest;
import bank.dto.insurance.calculator.InsuranceCalculatorTransactionResponse;
import bank.dto.insurance.pay.InsurancePayTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@AllArgsConstructor
public class InsuranceCalculatorController {


    private InsuranceCalculatorService insuranceCalculator;
    private InsurancePayService insurancePayService;

    @PutMapping("/users/{id}/insurances/operation=CALCULATE")
    public InsuranceCalculatorTransactionResponse calculate(
            @PathVariable("id") Integer idUser,
            @RequestBody @Valid InsuranceCalculatorRequest request) {

        log.debug("Received Insurance Calculator request: {}", request);
        log.debug("Id user: {}", idUser);

        return insuranceCalculator.calculate(request, idUser);
    }

    @PutMapping("/users/{id}/insurance/{idCreditCard}/operation=PAY")
    public InsurancePayTransactionResponse pay(@PathVariable("id") Integer idInsurance
            , @PathVariable("idCreditCard") Integer idCreditCard) {
        log.debug("Received Insurance Pay request: {}", idInsurance);
        return insurancePayService.pay(idInsurance, idCreditCard);
    }

}
