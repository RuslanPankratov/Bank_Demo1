package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TransactionToOtherCreditCard {

    public List<AddTransactionRequest> transaction(CreditCardEntity creditCardSenderEntity,
                                                   CreditCardEntity creditCardRecipientEntity,
                                                   BigDecimal departureAmount) {

        log.debug("Received Credit Card Entity Sender request: {}", creditCardSenderEntity);
        log.debug("Received Credit Card Entity Recipient request: {}", creditCardRecipientEntity);
        log.debug("Received Departure Amount request: {}", departureAmount);

        List<AddTransactionRequest> addTransactionRequests = convert(creditCardSenderEntity,
                creditCardRecipientEntity, departureAmount);

        if (creditCardSenderEntity.getInvoiceAmount().compareTo(departureAmount) > 0) {

            if (creditCardSenderEntity.getWithdrawalLimit().compareTo(departureAmount) > 0){
                creditCardSenderEntity.setInvoiceAmount(creditCardSenderEntity.getInvoiceAmount().
                        subtract(departureAmount));
                creditCardRecipientEntity.setInvoiceAmount(creditCardRecipientEntity.getInvoiceAmount().
                        add(departureAmount));
                addTransactionRequests.get(0).setTransactionSuccess(TransactionSuccess.SUCCESSFUL);
                addTransactionRequests.get(1).setTransactionSuccess(TransactionSuccess.SUCCESSFUL);

                log.debug("Changed Credit Card Entity Sender request: {}", creditCardSenderEntity);
                log.debug("Changed Credit Card Entity Recipient request: {}", creditCardRecipientEntity);
            } else {
                addTransactionRequests.get(0).setTransactionSuccess(TransactionSuccess.TRANSACTION_LIMIT_EXCEEDED);
                addTransactionRequests.get(1).setTransactionSuccess(TransactionSuccess.TRANSACTION_LIMIT_EXCEEDED);
            }

        }
        log.debug("Return Add Transaction Request: {}", addTransactionRequests);

        return addTransactionRequests;
    }


    private List<AddTransactionRequest> convert(CreditCardEntity creditCardSenderEntity,
                                                CreditCardEntity creditCardRecipientEntity,
                                                BigDecimal departureAmount) {
        List<AddTransactionRequest> transactionRequests = new ArrayList<>();

        AddTransactionRequest transactionSender = new AddTransactionRequest(departureAmount, TransactionType.SENDING,
                BetweenWhomTheTransaction.OTHER_PEOPLE, TransactionSuccess.NOT_ENOUGH_MONEY,
                creditCardSenderEntity.getIdUser());

        AddTransactionRequest transactionRecipient = new AddTransactionRequest(departureAmount,
                TransactionType.RECEIVING, BetweenWhomTheTransaction.OTHER_PEOPLE, TransactionSuccess.NOT_ENOUGH_MONEY,
                creditCardRecipientEntity.getIdUser());

        transactionRequests.add(transactionSender);
        transactionRequests.add(transactionRecipient);

        return transactionRequests;
    }

}
