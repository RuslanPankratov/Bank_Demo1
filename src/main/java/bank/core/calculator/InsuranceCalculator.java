package bank.core.calculator;

import bank.domain.InsuranceEntity;
import bank.core.strategy.insuranceCalculator.InsuranceCalculatorStrategy;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.InsuranceType;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.enum_class.WithWhomTheDeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
@Data
@AllArgsConstructor
@Slf4j
public class InsuranceCalculator {

    List<InsuranceCalculatorStrategy> strategies;

    public AddTransactionRequest insuranceCalculate(InsuranceEntity entity, BigDecimal sum
            , InsuranceType insuranceType) {
        log.debug("Received Insurance Entity request: {}", entity);
        log.debug("Received Sum request: {}", sum);
        log.debug("Received Type Insurance request: {}", insuranceType);

        for (int i = 0; i < strategies.size(); i++) {
            strategies.get(i).action(insuranceType, entity, sum);
        }

        return new AddTransactionRequest(sum, TransactionType.RECEIVING,
                WithWhomTheDeal.INSURANCE, TransactionSuccess.SUCCESSFUL, entity.getIdUser());
    }
}
