package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.dto.transaction.AddTransactionRequest;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DepositOrWithdrawalCalculator {

    public AddTransactionRequest calculator(CreditCardEntity creditCardEntity, AddTransactionRequest request) {

        log.debug("Received Credit Card Entity request: {}", creditCardEntity);
        log.debug("Received Credit Add Transaction request: {}", request);

        if (request.getTransactionType().equals(TransactionType.DEPOSIT)) {
            calculatorDeposit(creditCardEntity, request);
            request.setTransactionSuccess(TransactionSuccess.SUCCESSFUL);
        } else if (request.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
            calculatorWithdrawal(creditCardEntity, request);
        }

        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        log.debug("Changed Credit Add Transaction request: {}", request);

        return request;
    }

    private void calculatorDeposit(CreditCardEntity creditCardEntity, AddTransactionRequest request) {
        creditCardEntity.setInvoiceAmount(creditCardEntity.getInvoiceAmount().add(request.getAmount()));
    }

    private void calculatorWithdrawal(CreditCardEntity creditCardEntity, AddTransactionRequest request) {
        if (creditCardEntity.getInvoiceAmount().compareTo(request.getAmount()) >= 0) {
            if (creditCardEntity.getWithdrawalLimit().compareTo(request.getAmount()) > 0) {
                creditCardEntity.setInvoiceAmount(creditCardEntity.getInvoiceAmount().subtract(request.getAmount()));
                request.setTransactionSuccess(TransactionSuccess.SUCCESSFUL);
            } else {
                request.setTransactionSuccess(TransactionSuccess.TRANSACTION_LIMIT_EXCEEDED);
            }
        } else {
            request.setTransactionSuccess(TransactionSuccess.NOT_ENOUGH_MONEY);
        }
    }

}
