package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class DepositOrWithdrawalCalculatorTest {

    DepositOrWithdrawalCalculator depositOrWithdrawalCalculator = new DepositOrWithdrawalCalculator();

    @Test
    void calculatorDepositTest() {

        CreditCardEntity creditCardEntity = new CreditCardEntity(2, "log", "pas",
                new BigDecimal(20000),
                new BigDecimal(2000), 1);

        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(new BigDecimal(2000),
                TransactionType.DEPOSIT, BetweenWhomTheTransaction.INSIDE, TransactionSuccess.NOT_ENOUGH_MONEY
                , 1);

        depositOrWithdrawalCalculator.calculator(creditCardEntity, addTransactionRequest);

        assertEquals(creditCardEntity.getInvoiceAmount(), new BigDecimal(22000));
    }


    @Test
    void calculatorWithdrawalSuccessfulTest() {

        CreditCardEntity creditCardEntity = new CreditCardEntity(2, "log", "pas",
                new BigDecimal(20000),
                new BigDecimal(3000), 1);

        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(new BigDecimal(2000),
                TransactionType.WITHDRAWAL, BetweenWhomTheTransaction.INSIDE, TransactionSuccess.NOT_ENOUGH_MONEY
                , 1);

        depositOrWithdrawalCalculator.calculator(creditCardEntity, addTransactionRequest);

        assertEquals(creditCardEntity.getInvoiceAmount(), new BigDecimal(18000));
    }


    @Test
    void calculatorWithdrawalLimitTest() {

        CreditCardEntity creditCardEntity = new CreditCardEntity(2, "log", "pas",
                new BigDecimal(20000),
                new BigDecimal(2000), 1);

        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(new BigDecimal(2000),
                TransactionType.WITHDRAWAL, BetweenWhomTheTransaction.INSIDE, TransactionSuccess.NOT_ENOUGH_MONEY
                , 1);

        depositOrWithdrawalCalculator.calculator(creditCardEntity, addTransactionRequest);

        assertEquals(creditCardEntity.getInvoiceAmount(), new BigDecimal(20000));
    }


    @Test
    void calculatorWithdrawalNotEnoughMoneyTest() {

        CreditCardEntity creditCardEntity = new CreditCardEntity(2, "log", "pas",
                new BigDecimal(20000),
                new BigDecimal(30000), 1);

        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(new BigDecimal(20001),
                TransactionType.WITHDRAWAL, BetweenWhomTheTransaction.INSIDE, TransactionSuccess.NOT_ENOUGH_MONEY
                , 1);

        depositOrWithdrawalCalculator.calculator(creditCardEntity, addTransactionRequest);

        assertEquals(creditCardEntity.getInvoiceAmount(), new BigDecimal(20000));
    }
}