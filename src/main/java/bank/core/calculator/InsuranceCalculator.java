package bank.core.calculator;

import bank.domain.InsuranceEntity;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.WithWhomTheDeal;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.enum_class.InsuranceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Component
@Data
@AllArgsConstructor
@Slf4j
public class InsuranceCalculator {
    public AddTransactionRequest insuranceCalculate(InsuranceEntity entity, BigDecimal sum
            , InsuranceType typeInsurance) {
        log.debug("Received Insurance Entity request: {}", entity);
        log.debug("Received Sum request: {}", sum);
        log.debug("Received Type Insurance request: {}", typeInsurance);

        if (typeInsurance.equals(InsuranceType.HOUSE)) {
            return houses(entity, sum);

        } else if (typeInsurance.equals(InsuranceType.ITEMS)) {
            return items(entity, sum);

        } else if (typeInsurance.equals(InsuranceType.HEALTH)) {
            return health(entity, sum);

        } else {
            return car(entity, sum);

        }
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
                WithWhomTheDeal.INSURANCE, TransactionSuccess.SUCCESSFUL, entity.getIdUser());
    }
}
