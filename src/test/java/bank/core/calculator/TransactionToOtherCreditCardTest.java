package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.TransactionSuccess;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class TransactionToOtherCreditCardTest {

    private TransactionToOtherCreditCard transactionToOtherCreditCard = new TransactionToOtherCreditCard();

    @Test
    void transactionSuccessfulTest() {

        CreditCardEntity creditCardSenderEntity = addCreditCardSender();
        CreditCardEntity creditCardRecipientEntity = addCreditCardRecipient();
        List<AddTransactionRequest> addTransactionRequests =
                transactionToOtherCreditCard.transaction(creditCardSenderEntity,
                        creditCardRecipientEntity, new BigDecimal(900));

        BigDecimal resultSender = creditCardSenderEntity.getInvoiceAmount().setScale(2, RoundingMode.CEILING);
        BigDecimal resultRecipient = creditCardRecipientEntity.getInvoiceAmount().setScale(2
                , RoundingMode.CEILING);

        BigDecimal expectedResultSender = new BigDecimal(3100).setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResultRecipient = new BigDecimal(4900).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResultSender, resultSender);
        assertEquals(expectedResultRecipient, resultRecipient);
        assertEquals(addTransactionRequests.get(0).getTransactionSuccess(), TransactionSuccess.SUCCESSFUL);
    }


    @Test
    void transactionLimitExceededTest() {

        CreditCardEntity creditCardSenderEntity = addCreditCardSender();
        CreditCardEntity creditCardRecipientEntity = addCreditCardRecipient();

        List<AddTransactionRequest> addTransactionRequests =
                transactionToOtherCreditCard.transaction(creditCardSenderEntity, creditCardRecipientEntity
                        , new BigDecimal(1100));

        BigDecimal resultSender = creditCardSenderEntity.getInvoiceAmount().setScale(2, RoundingMode.CEILING);
        BigDecimal resultRecipient = creditCardRecipientEntity.getInvoiceAmount().setScale(2
                , RoundingMode.CEILING);

        BigDecimal expectedResultSender = new BigDecimal(4000).setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResultRecipient = new BigDecimal(4000).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResultSender, resultSender);
        assertEquals(expectedResultRecipient, resultRecipient);
        assertEquals(addTransactionRequests.get(0).getTransactionSuccess(),
                TransactionSuccess.TRANSACTION_LIMIT_EXCEEDED);
    }


    @Test
    void transactionNotEnoughMoneyTest() {

        CreditCardEntity creditCardSenderEntity = addCreditCardSender();
        CreditCardEntity creditCardRecipientEntity = addCreditCardRecipient();
        creditCardSenderEntity.setInvoiceAmount(new BigDecimal(890));
        List<AddTransactionRequest> addTransactionRequests =
                transactionToOtherCreditCard.transaction(creditCardSenderEntity, creditCardRecipientEntity
                        , new BigDecimal(900));

        BigDecimal resultSender = creditCardSenderEntity.getInvoiceAmount().setScale(2, RoundingMode.CEILING);
        BigDecimal resultRecipient = creditCardRecipientEntity.getInvoiceAmount().setScale(2
                , RoundingMode.CEILING);

        BigDecimal expectedResultSender = new BigDecimal(890).setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResultRecipient = new BigDecimal(4000).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResultSender, resultSender);
        assertEquals(expectedResultRecipient, resultRecipient);
        assertEquals(addTransactionRequests.get(0).getTransactionSuccess(),
                TransactionSuccess.NOT_ENOUGH_MONEY);
    }


    private CreditCardEntity addCreditCardSender() {
        return new CreditCardEntity(2, "login", "pas"
                , new BigDecimal(4000), new BigDecimal(1000), 2);
    }


    private CreditCardEntity addCreditCardRecipient() {
        return new CreditCardEntity(3, "login", "pas"
                , new BigDecimal(4000), new BigDecimal(1000), 2);
    }

}