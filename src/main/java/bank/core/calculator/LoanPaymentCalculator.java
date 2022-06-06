package bank.core.calculator;


import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class LoanPaymentCalculator {

    public AddTransactionRequest methodPay(CreditEntity creditEntity, CreditCardEntity creditCardEntity) {

        log.debug("Received Credit Entity request: {}", creditEntity);
        log.debug("Received Credit Card Entity request: {}", creditCardEntity);

        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(creditEntity.getPaymentPerMonth(),
                TransactionType.PAY, BetweenWhomTheTransaction.CREDIT, TransactionSuccess.NOT_ENOUGH_MONEY,
                creditEntity.getIdUser());

        if (creditCardEntity.getInvoiceAmount().compareTo(creditEntity.getPaymentPerMonth()) > 0) {
            creditEntity.setCountMonthsToPay(creditEntity.getCountMonthsToPay().subtract(new BigDecimal(1)));
            BigDecimal paidMonth = creditEntity.getPaymentPerMonth();
            creditEntity.setPaid(creditEntity.getPaid().add(paidMonth));
            creditEntity.setHowMuchToPay(creditEntity.getHowMuchToPay().subtract(paidMonth));
            creditCardEntity.setInvoiceAmount(creditCardEntity.getInvoiceAmount().subtract(paidMonth));
            addTransactionRequest.setTransactionSuccess(TransactionSuccess.SUCCESSFUL);

            log.debug("Changed Credit Entity request: {}", creditEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        }
        log.debug("Return Add Transaction Request: {}", addTransactionRequest);

        return addTransactionRequest;
    }

}
