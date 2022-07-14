package bank.core.strategy.insuranceCalculator;


import bank.domain.InsuranceEntity;
import bank.enum_class.InsuranceType;

import java.math.BigDecimal;

public interface InsuranceCalculatorStrategy {

    void action(InsuranceType insuranceType, InsuranceEntity entity, BigDecimal sum);

}
