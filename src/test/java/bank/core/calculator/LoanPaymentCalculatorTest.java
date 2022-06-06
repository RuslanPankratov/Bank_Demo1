package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LoanPaymentCalculatorTest {

    private LoanPaymentCalculator loanPaymentCalculator = new LoanPaymentCalculator();

    @Test
    void methodPayEnoughAmount() {
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();

        loanPaymentCalculator.methodPay(credit, creditCardEntity);

        BigDecimal result = creditCardEntity.getInvoiceAmount().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(3882.10).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void methodPayNotEnoughAmount() {
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();
        creditCardEntity.setInvoiceAmount(new BigDecimal(100));

        loanPaymentCalculator.methodPay(credit, creditCardEntity);

        BigDecimal result = creditCardEntity.getInvoiceAmount().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(100).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    private CreditEntity addCreditEntity() {
        return new CreditEntity(2, new BigDecimal(11790.9500), new BigDecimal(2.1500)
                , new BigDecimal(0), new BigDecimal(11790.9500), new BigDecimal(100.0000)
                , new BigDecimal(1790.9500), new BigDecimal(10000.0000), new BigDecimal(117.9100), 2);
    }

    private CreditCardEntity addCreditCard() {
        return new CreditCardEntity(2, "login", "pas"
                , new BigDecimal(4000), new BigDecimal(1000), 2);
    }

}