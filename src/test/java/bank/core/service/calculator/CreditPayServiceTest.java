package bank.core.service.calculator;

import bank.core.calculator.LoanPaymentCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.core.service.credit.dto.credit.creditPay.CreditPayTransactionResponse;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
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

        when(creditCardRepository.findByIdUser(any())).thenReturn(Optional.of(creditCardEntity));
        when(creditRepository.findByIdUser(any())).thenReturn(Optional.of(creditEntity));
        when(loanPaymentCalculator.methodPay(any(), any())).thenReturn(addTransactionRequestConvert());

        Optional<CreditPayTransactionResponse> result = creditPayService.pay(3);
        Optional<CreditPayTransactionResponse> expectedResult = convert();

        assertEquals(expectedResult, result);

        verify(creditCardRepository).findByIdUser(any());
        verify(creditCardRepository).save(any());
        verify(creditRepository).findByIdUser(any());
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
        return new CreditEntity(23, new BigDecimal(11790.95), new BigDecimal("2.15"), new BigDecimal(0)
                , new BigDecimal(11790), new BigDecimal(100), new BigDecimal(1790)
                , new BigDecimal(10000), new BigDecimal(117.91), 3);
    }

    private Optional<CreditPayTransactionResponse> convert() {
        CreditPayTransactionResponse creditPayTransactionResponse = new CreditPayTransactionResponse(
                new BigDecimal(100), TransactionType.PAY, WithWhomTheDeal.INSURANCE
                , TransactionSuccess.SUCCESSFUL, 1);

        return Optional.of(creditPayTransactionResponse);
    }


    private AddTransactionRequest addTransactionRequestConvert() {
        return new AddTransactionRequest(new BigDecimal(100)
                , TransactionType.PAY, WithWhomTheDeal.INSURANCE, TransactionSuccess.SUCCESSFUL, 1);
    }

}