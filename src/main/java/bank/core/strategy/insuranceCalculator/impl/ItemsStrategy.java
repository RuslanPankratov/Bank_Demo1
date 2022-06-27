package bank.core.strategy.insuranceCalculator.impl;

import bank.core.strategy.insuranceCalculator.HelperInsuranceCalculateStrategy;
import bank.core.strategy.insuranceCalculator.InsuranceCalculatorStrategy;
import bank.domain.InsuranceEntity;
import bank.enum_class.InsuranceType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ItemsStrategy implements InsuranceCalculatorStrategy {

    HelperInsuranceCalculateStrategy helperInsuranceCalculateStrategy = new HelperInsuranceCalculateStrategy();

    @Override
    public void action(InsuranceType insuranceType, InsuranceEntity entity
            , BigDecimal sum) {
        if (insuranceType.equals(InsuranceType.ITEMS)) {
            helperInsuranceCalculateStrategy.calculate(entity, sum, new BigDecimal(100));
        }
    }
}
