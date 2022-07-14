package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.domain.InsuranceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PayForInsuranceTest {

    private PayForInsurance payForInsurance = new PayForInsurance();

    @Test
    void payInsuranceSuccessful() {
        InsuranceEntity insurance = addInsurance();
        CreditCardEntity creditCardEntity = addCreditCard(4000);

        payForInsurance.payInsurance(insurance, creditCardEntity);

        BigDecimal result = creditCardEntity.getInvoiceAmount().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(3000).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }


    @Test
    void payInsuranceNotEnoughMoney() {
        InsuranceEntity insurance = addInsurance();
        CreditCardEntity creditCardEntity = addCreditCard(900);

        payForInsurance.payInsurance(insurance, creditCardEntity);

        BigDecimal result = creditCardEntity.getInvoiceAmount().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(900).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }


    private InsuranceEntity addInsurance() {
        return new InsuranceEntity(2, new BigDecimal(2000), new BigDecimal(1000),
                2);
    }

    private CreditCardEntity addCreditCard(Integer invoiceAmount) {
        return new CreditCardEntity(2, "login", "pas"
                , BigDecimal.valueOf(invoiceAmount), new BigDecimal(1000), 2);
    }

}