package bank.core.calculator;

import bank.domain.InsuranceEntity;
import bank.enum_class.TypeInsurance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;


@ExtendWith(MockitoExtension.class)
class InsuranceCalculatorTest {

    private InsuranceCalculator insuranceCalculator = new InsuranceCalculator();


    @Test
    void insuranceCalculatorHouseTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insurance(insurance, new BigDecimal(20000), TypeInsurance.HOUSE);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(100).setScale(2, RoundingMode.CEILING);
        assertEquals(expectedResult, result);
    }

    @Test
    void insuranceCalculatorItemsTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insurance(insurance, new BigDecimal(20000), TypeInsurance.ITEMS);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(200).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void insuranceCalculatorHealthTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insurance(insurance, new BigDecimal(20000), TypeInsurance.HEALTH);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(181.82).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void insuranceCalculatorCarTest() {
        InsuranceEntity insurance = new InsuranceEntity(1, new BigDecimal(0), new BigDecimal(0)
                , 1);

        insuranceCalculator.insurance(insurance, new BigDecimal(20000), TypeInsurance.CAR);

        BigDecimal result = insurance.getInsurancePaid().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(1000).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

}