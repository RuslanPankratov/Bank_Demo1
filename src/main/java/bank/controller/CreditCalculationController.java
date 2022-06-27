package bank.controller;

import bank.core.service.calculator.CreditPayService;
import bank.core.service.calculator.LoanCalculationService;
import bank.dto.credit.creditPay.CreditPayTransactionResponse;
import bank.dto.credit.loan.CreditLoanCalculationTransactionResponse;
import bank.dto.credit.loan.CreditLoanRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
public class CreditCalculationController {

    private LoanCalculationService loanCalculationService;
    private CreditPayService creditPayService;

    @PutMapping("/users/{id}/credits/{idCredit}/creditCards/{idCreditCard}/operation=CALCULATE")
    public CreditLoanCalculationTransactionResponse calculate(@PathVariable("id") Integer idUser
            , @PathVariable("idCredit") Integer idCredit, @PathVariable("idCreditCard") Integer idCreditCard
            , @RequestBody @Valid CreditLoanRequest request) {
        log.debug("Received calculation credit request: {}", request);
        log.debug("Id User: {}", idUser);
        return loanCalculationService.loan(request, idUser, idCredit, idCreditCard);
    }

    @PutMapping("/credits/{id}/creditCards/{idCreditCard}/operation=PAY")
    public CreditPayTransactionResponse pay(@PathVariable("id") Integer idCredit
            , @PathVariable("idCreditCard") Integer idCreditCard) {
        log.debug("Received pay credit request: {}", idCredit);
        return creditPayService.pay(idCredit, idCreditCard);
    }

}
