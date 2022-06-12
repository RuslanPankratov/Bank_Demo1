package bank.controller.service;

import bank.core.service.calculator.CreditPayService;
import bank.core.service.calculator.LoanCalculationService;
import bank.core.service.credit.dto.credit.creditPay.CreditPayTransactionResponse;
import bank.core.service.credit.dto.credit.loan.CreditLoanCalculationTransactionResponse;
import bank.core.service.credit.dto.credit.loan.CreditLoanRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class CreditCalculationController {

    private LoanCalculationService loanCalculationService;
    private CreditPayService creditPayService;

    @PutMapping("/{id}/credits/operation=CALCULATE")
    public Optional<CreditLoanCalculationTransactionResponse> calculate(@PathVariable("id") Integer idUser,
                                                                        @RequestBody @Valid CreditLoanRequest request) {
        log.debug("Received calculation credit request: {}", request);
        log.debug("Id User: {}", idUser);
        return loanCalculationService.loan(request, idUser);
    }

    @PutMapping("/{id}/credits/operation=PAY")
    public Optional<CreditPayTransactionResponse> pay(@PathVariable("id") Integer idUser) {
        log.debug("Received pay credit request: {}", idUser);
        return creditPayService.pay(idUser);
    }

}
