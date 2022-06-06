package bank.controller.service;


import bank.core.service.calculator.DepositOrWithdrawalService;
import bank.core.service.calculator.TransactionToOtherCreditCardService;
import bank.dto.creditCard.CreditCardToOtherRequest;
import bank.dto.transaction.DepositOrWithdrawalCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.dto.transaction.add.ListAddTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
public class MiscellaneousTransactionsController {

    private final DepositOrWithdrawalService depositOrWithdrawalService;
    private final TransactionToOtherCreditCardService transactionToOtherCreditCardService;

    @PutMapping("/withdrawOrDeposit")
    public Optional<DepositOrWithdrawalCalculatorTransactionResponse> withdrawOrDeposit(
            @RequestBody @Valid AddTransactionRequest request) {
        log.debug("Received Withdraw Or Deposit request: {}", request);
        return depositOrWithdrawalService.depositOrWithdrawal(request);
    }

    @PutMapping("/sender")
    public Optional<ListAddTransactionResponse> sender(@RequestBody @Valid CreditCardToOtherRequest request) {
        log.debug("Received Sender request: {}", request);
        return transactionToOtherCreditCardService.transaction(request);
    }

}
