package bank.controller;


import bank.core.service.calculator.DepositService;
import bank.core.service.calculator.TransactionToOtherCreditCardService;
import bank.core.service.calculator.WithdrawalService;
import bank.dto.creditCard.CreditCardToOtherRequest;
import bank.dto.transaction.DepositCalculatorTransactionResponse;
import bank.dto.transaction.WithdrawalCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.dto.transaction.add.ListAddTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@AllArgsConstructor
@RestController
public class MiscellaneousTransactionsController {

    private final DepositService depositService;
    private final WithdrawalService withdrawalService;
    private final TransactionToOtherCreditCardService transactionToOtherCreditCardService;

    @PutMapping("/creditCards/{id}/operation=DEPOSIT")
    public DepositCalculatorTransactionResponse deposit(
            @PathVariable("id") Integer idUser,
            @RequestBody @Valid AddTransactionRequest request) {

        log.debug("Received Deposit request: {}", request);
        log.debug("Id User: {}", idUser);
        return depositService.deposit(request, idUser);
    }


    @PutMapping("/creditCards/{id}/operation=WITHDRAWAL")
    public WithdrawalCalculatorTransactionResponse withdrawal(
            @PathVariable("id") Integer idUser,
            @RequestBody @Valid AddTransactionRequest request) {

        log.debug("Received Withdraw request: {}", request);
        log.debug("Id User: {}", idUser);
        return withdrawalService.withdrawal(request, idUser);
    }

    @PutMapping("/userSender/{idSender}/usersRecipient/{idRecipient}/creditCards/operation=SENDER")
    public ListAddTransactionResponse sender(@PathVariable("idSender") Integer idSender,
                                                       @PathVariable("idRecipient") Integer idRecipient,
                                                       @RequestBody @Valid CreditCardToOtherRequest request) {
        log.debug("Received Sender request: {}", request);
        log.debug("Id sender: {}", idSender);
        log.debug("Id recipient: {}", idRecipient);
        return transactionToOtherCreditCardService.transaction(request, idSender, idRecipient);
    }

}
