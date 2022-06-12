package bank.controller.service;


import bank.core.service.calculator.DepositService;
import bank.core.service.calculator.TransactionToOtherCreditCardService;
import bank.core.service.calculator.WithdrawalService;
import bank.core.service.credit.dto.creditCard.CreditCardToOtherRequest;
import bank.core.service.credit.dto.transaction.DepositCalculatorTransactionResponse;
import bank.core.service.credit.dto.transaction.WithdrawalCalculatorTransactionResponse;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
import bank.core.service.credit.dto.transaction.add.ListAddTransactionResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class MiscellaneousTransactionsController {

    private final DepositService depositService;
    private final WithdrawalService withdrawalService;
    private final TransactionToOtherCreditCardService transactionToOtherCreditCardService;

    @PutMapping("{id}/creditCards/operation=DEPOSIT")
    public Optional<DepositCalculatorTransactionResponse> deposit(
            @PathVariable("id") Integer idUser,
            @RequestBody @Valid AddTransactionRequest request) {

        log.debug("Received Deposit request: {}", request);
        log.debug("Id User: {}", idUser);
        return depositService.deposit(request, idUser);
    }


    @PutMapping("{id}/creditCards/operation=WITHDRAWAL")
    public Optional<WithdrawalCalculatorTransactionResponse> withdrawal(
            @PathVariable("id") Integer idUser,
            @RequestBody @Valid AddTransactionRequest request) {

        log.debug("Received Withdraw request: {}", request);
        log.debug("Id User: {}", idUser);
        return withdrawalService.withdrawal(request, idUser);
    }

    @PutMapping("{idSender}/usersRecipient/{idRecipient}/creditCards/operation=SENDER")
    public Optional<ListAddTransactionResponse> sender(@PathVariable("idSender") Integer idSender,
                                                       @PathVariable("idRecipient") Integer idRecipient,
                                                       @RequestBody @Valid CreditCardToOtherRequest request) {
        log.debug("Received Sender request: {}", request);
        log.debug("Id sender: {}", idSender);
        log.debug("Id recipient: {}", idRecipient);
        return transactionToOtherCreditCardService.transaction(request, idSender, idRecipient);
    }

}
