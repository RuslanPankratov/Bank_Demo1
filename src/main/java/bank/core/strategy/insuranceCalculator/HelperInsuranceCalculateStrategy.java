package bank.core.strategy.insuranceCalculator;

import bank.domain.InsuranceEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Slf4j
public class HelperInsuranceCalculateStrategy {
    public void calculate(InsuranceEntity entity, BigDecimal sum, BigDecimal percent) {
        BigDecimal howMuchToPay = sum.divide(percent, 3, RoundingMode.UP);
        BigDecimal sumInsured = entity.getSumInsured().add(sum);
        entity.setSumInsured(sumInsured);
        BigDecimal paid = entity.getInsurancePaid().add(howMuchToPay);
        entity.setInsurancePaid(paid);
        log.debug("Changed Insurance Entity request: {}", entity);
    }
}
