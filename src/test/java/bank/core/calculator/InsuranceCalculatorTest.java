package bank.core.calculator;

import bank.domain.InsuranceEntity;
import bank.core.strategy.insuranceCalculator.InsuranceCalculatorStrategy;
import bank.core.strategy.insuranceCalculator.impl.CarStrategy;
import bank.core.strategy.insuranceCalculator.impl.HealthStrategy;
import bank.core.strategy.insuranceCalculator.impl.HousesStrategy;
import bank.core.strategy.insuranceCalculator.impl.ItemsStrategy;
import bank.enum_class.InsuranceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class InsuranceCalculatorTest {

    @Autowired
    private InsuranceCalculator insuranceCalculator = new InsuranceCalculator(convert());


    @Test
    void insuranceCalculatorHouseTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insuranceCalculate(insurance, new BigDecimal(20000), InsuranceType.HOUSE);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(100).setScale(2, RoundingMode.CEILING);
        assertEquals(expectedResult, result);
    }

    @Test
    void insuranceCalculatorItemsTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insuranceCalculate(insurance, new BigDecimal(20000), InsuranceType.ITEMS);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(200).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void insuranceCalculatorHealthTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insuranceCalculate(insurance, new BigDecimal(20000), InsuranceType.HEALTH);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(181.82).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void insuranceCalculatorCarTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insuranceCalculate(insurance, new BigDecimal(20000), InsuranceType.CAR);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(1000).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }


    private List<InsuranceCalculatorStrategy> convert(){
        return List.of(new CarStrategy(),
                new HealthStrategy(),
                new HousesStrategy(),
                new ItemsStrategy());
    }
}