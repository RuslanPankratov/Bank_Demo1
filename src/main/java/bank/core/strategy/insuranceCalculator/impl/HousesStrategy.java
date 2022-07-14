package bank.core.strategy.insuranceCalculator.impl;

import bank.core.strategy.insuranceCalculator.HelperInsuranceCalculateStrategy;
import bank.domain.InsuranceEntity;
import bank.core.strategy.insuranceCalculator.InsuranceCalculatorStrategy;
import bank.enum_class.InsuranceType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HousesStrategy implements InsuranceCalculatorStrategy {
    HelperInsuranceCalculateStrategy helperInsuranceCalculateStrategy = new HelperInsuranceCalculateStrategy();

    @Override
    public void action(InsuranceType insuranceType, InsuranceEntity entity
            , BigDecimal sum) {
        if (insuranceType.equals(InsuranceType.HOUSE)) {
            helperInsuranceCalculateStrategy.calculate(entity, sum, new BigDecimal(200));
        }
    }
}
