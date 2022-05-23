package bank.core.calculator;

import bank.domain.InsuranceEntity;
import bank.dto.transaction.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.enum_class.TypeInsurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Service
@Data
@AllArgsConstructor
@Slf4j
public class InsuranceCalculator {
    public AddTransactionRequest insurance(InsuranceEntity entity, BigDecimal sum, TypeInsurance typeInsurance) {
        log.debug("Received Insurance Entity request: {}", entity);
        log.debug("Received Sum request: {}", sum);
        log.debug("Received Type Insurance request: {}", typeInsurance);

        if (typeInsurance.equals(TypeInsurance.HOUSE)) {
            return houses(entity, sum);

        } else if (typeInsurance.equals(TypeInsurance.ITEMS)) {
            return items(entity, sum);

        } else if (typeInsurance.equals(TypeInsurance.HEALTH)) {
            return health(entity, sum);

        } else if (typeInsurance.equals(TypeInsurance.CAR)) {
            return car(entity, sum);

        }
        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(sum, TransactionType.RECEIVING,
                BetweenWhomTheTransaction.INSURANCE, TransactionSuccess.NO_TYPE_OF_INSURANCE, entity.getIdUser());
        log.debug("Return Add Transaction Request: {}", addTransactionRequest);

        return addTransactionRequest;
    }


    private AddTransactionRequest houses(InsuranceEntity entity, BigDecimal sum) {
        return calculate(entity, sum, new BigDecimal(200));
    }

    private AddTransactionRequest items(InsuranceEntity entity, BigDecimal sum) {
        return calculate(entity, sum, new BigDecimal(100));
    }

    private AddTransactionRequest health(InsuranceEntity entity, BigDecimal sum) {
        return calculate(entity, sum, new BigDecimal(110));
    }

    private AddTransactionRequest car(InsuranceEntity entity, BigDecimal sum) {
        return calculate(entity, sum, new BigDecimal(20));
    }


    private AddTransactionRequest calculate(InsuranceEntity entity, BigDecimal sum, BigDecimal percent) {
        BigDecimal howMuchToPay = sum.divide(percent, 3, RoundingMode.UP);
        BigDecimal sumInsured = entity.getSumInsured().add(sum);
        entity.setSumInsured(sumInsured);
        BigDecimal paid = entity.getInsurancePaid().add(howMuchToPay);
        entity.setInsurancePaid(paid);
        log.debug("Changed Insurance Entity request: {}", entity);

        return new AddTransactionRequest(sum, TransactionType.RECEIVING,
                BetweenWhomTheTransaction.INSURANCE, TransactionSuccess.SUCCESSFUL, entity.getIdUser());
    }
}
