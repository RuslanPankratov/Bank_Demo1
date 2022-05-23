package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.domain.InsuranceEntity;
import bank.dto.transaction.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class PayForInsurance {


    public AddTransactionRequest payInsurance(InsuranceEntity insuranceEntity, CreditCardEntity creditCardEntity) {

        log.debug("Received Insurance Entity request: {}", insuranceEntity);
        log.debug("Received Credit Card Entity request: {}", creditCardEntity);

        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(insuranceEntity.getInsurancePaid(),
                TransactionType.PAY, BetweenWhomTheTransaction.INSURANCE, TransactionSuccess.NOT_ENOUGH_MONEY,
                insuranceEntity.getIdUser());

        if (insuranceEntity.getInsurancePaid().compareTo(creditCardEntity.getInvoiceAmount()) <= 0) {
            creditCardEntity.setInvoiceAmount(creditCardEntity.getInvoiceAmount().subtract(
                    insuranceEntity.getInsurancePaid()));
            insuranceEntity.setInsurancePaid(new BigDecimal(0));
            addTransactionRequest.setTransactionSuccess(TransactionSuccess.NOT_ENOUGH_MONEY);

            log.debug("Changed Insurance Entity request: {}", insuranceEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        }
        log.debug("Return Add Transaction Request: {}", addTransactionRequest);

        return addTransactionRequest;
    }
}
