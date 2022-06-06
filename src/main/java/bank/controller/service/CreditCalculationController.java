package bank.controller.service;

import bank.core.service.calculator.CreditPayService;
import bank.core.service.calculator.LoanCalculationService;
import bank.dto.credit.creditPay.CreditPayTransactionResponse;
import bank.dto.credit.loan.CreditLoanCalculationTransactionResponse;
import bank.dto.credit.loan.CreditLoanRequest;
import bank.dto.credit.creditPay.CreditPayRequest;
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
public class CreditCalculationController {

    private LoanCalculationService loanCalculationService;
    private CreditPayService creditPayService;

    @PutMapping("/creditCalculation")
    public Optional<CreditLoanCalculationTransactionResponse> calculate(@RequestBody @Valid CreditLoanRequest request) {
        log.debug("Received calculation credit request: {}", request);
        return loanCalculationService.loan(request);
    }

    @PutMapping("/creditPay")
    public Optional<CreditPayTransactionResponse> pay(@RequestBody @Valid CreditPayRequest request) {
        log.debug("Received pay credit request: {}", request);
        return creditPayService.pay(request);
    }

}
