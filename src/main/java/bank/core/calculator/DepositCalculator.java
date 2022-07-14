package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DepositCalculator {

    public AddTransactionRequest depositCalculator(CreditCardEntity creditCardEntity, AddTransactionRequest request) {

        log.debug("Received Credit Card Entity request: {}", creditCardEntity);
        log.debug("Received Credit Add Transaction request: {}", request);

        calculatorDeposit(creditCardEntity, request);

        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        log.debug("Changed Credit Add Transaction request: {}", request);

        return request;
    }

    private void calculatorDeposit(CreditCardEntity creditCardEntity, AddTransactionRequest request) {
        creditCardEntity.setInvoiceAmount(creditCardEntity.getInvoiceAmount().add(request.getAmount()));
        request.setTransactionSuccess(TransactionSuccess.SUCCESSFUL);
        request.setTransactionType(TransactionType.DEPOSIT);
    }

}
