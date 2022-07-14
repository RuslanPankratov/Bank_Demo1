package bank.core.service.calculator;

import bank.core.calculator.LoanPaymentCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.dto.credit.creditPay.CreditPayTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.WithWhomTheDeal;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.repository.CreditCardRepository;
import bank.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditPayServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private AddTransactionService addTransactionService;
    @Mock
    private LoanPaymentCalculator loanPaymentCalculator;
    @InjectMocks
    private CreditPayService creditPayService;


    @Test
    void payTest() {
        CreditEntity creditEntity = creditConvert();
        CreditCardEntity creditCardEntity = creditCardConvert();

        when(creditCardRepository.findById(any())).thenReturn(Optional.of(creditCardEntity));
        when(creditRepository.findById(any())).thenReturn(Optional.of(creditEntity));
        when(loanPaymentCalculator.methodPay(any(), any())).thenReturn(addTransactionRequestConvert());

        CreditPayTransactionResponse result = creditPayService.pay(3,3);
        CreditPayTransactionResponse expectedResult = convert();

        assertEquals(expectedResult, result);

        verify(creditCardRepository).findById(any());
        verify(creditCardRepository).save(any());
        verify(creditRepository).findById(any());
        verify(creditRepository).save(any());
        verify(addTransactionService).save(any());
        verify(loanPaymentCalculator).methodPay(any(), any());

        verifyNoMoreInteractions(creditCardRepository, creditRepository, addTransactionService, loanPaymentCalculator);
    }

    private CreditCardEntity creditCardConvert() {
        return new CreditCardEntity(3, "log", "pass", new BigDecimal(10000)
                , new BigDecimal(1000), 3);
    }

    private CreditEntity creditConvert() {
        return new CreditEntity(23, new BigDecimal(11790.95), new BigDecimal("2.15")
                , new BigDecimal(0)
                , new BigDecimal(11790), new BigDecimal(100), new BigDecimal(1790)
                , new BigDecimal(10000), new BigDecimal(117.91), 3);
    }

    private CreditPayTransactionResponse convert() {
        return new CreditPayTransactionResponse(
                new BigDecimal(100), TransactionType.PAY, WithWhomTheDeal.INSURANCE
                , TransactionSuccess.SUCCESSFUL, 1);
    }


    private AddTransactionRequest addTransactionRequestConvert() {
        return new AddTransactionRequest(new BigDecimal(100)
                , TransactionType.PAY, WithWhomTheDeal.INSURANCE, TransactionSuccess.SUCCESSFUL, 1);
    }

}