package bank.core.service.calculator;

import bank.core.calculator.DepositOrWithdrawalCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.dto.transaction.DepositOrWithdrawalCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.repository.CreditCardRepository;
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
class DepositOrWithdrawalServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private AddTransactionService addTransactionService;
    @Mock
    private DepositOrWithdrawalCalculator depositOrWithdrawalCalculator;
    @InjectMocks
    private DepositOrWithdrawalService depositOrWithdrawalService;

    @Test
    void depositTest() {

        AddTransactionRequest request = requestConvert();
        CreditCardEntity creditCardEntity = creditCardConvert(3);

        when(creditCardRepository.findByIdUser(any())).thenReturn(Optional.of(creditCardEntity));
        when(depositOrWithdrawalCalculator.calculator(any(), any())).thenReturn(request);

        Optional<DepositOrWithdrawalCalculatorTransactionResponse> result
                = depositOrWithdrawalService.depositOrWithdrawal(request);
        Optional<DepositOrWithdrawalCalculatorTransactionResponse> expectedResult = transactionConvert(request);

        assertEquals(expectedResult, result);

        verify(creditCardRepository).findByIdUser(any());
        verify(creditCardRepository).save(any());
        verify(addTransactionService).transaction(any());
        verify(depositOrWithdrawalCalculator).calculator(any(), any());


        verifyNoMoreInteractions(creditCardRepository, addTransactionService, depositOrWithdrawalCalculator);
    }


    @Test
    void withdrawalTest() {

        AddTransactionRequest request = requestConvert();
        request.setTransactionType(TransactionType.WITHDRAWAL);
        CreditCardEntity creditCardEntity = creditCardConvert(3);

        when(creditCardRepository.findByIdUser(any())).thenReturn(Optional.of(creditCardEntity));
        when(depositOrWithdrawalCalculator.calculator(any(), any())).thenReturn(request);

        Optional<DepositOrWithdrawalCalculatorTransactionResponse> result =
                depositOrWithdrawalService.depositOrWithdrawal(request);
        Optional<DepositOrWithdrawalCalculatorTransactionResponse> expectedResult = transactionConvert(request);

        assertEquals(expectedResult, result);

        verify(creditCardRepository).findByIdUser(any());
        verify(creditCardRepository).save(any());
        verify(addTransactionService).transaction(any());
        verify(depositOrWithdrawalCalculator).calculator(any(), any());


        verifyNoMoreInteractions(creditCardRepository, addTransactionService, depositOrWithdrawalCalculator);
    }

    private AddTransactionRequest requestConvert() {
        return new AddTransactionRequest(new BigDecimal(1000), TransactionType.DEPOSIT
                , BetweenWhomTheTransaction.INSIDE, TransactionSuccess.SUCCESSFUL, 3);
    }

    private CreditCardEntity creditCardConvert(Integer id) {
        return new CreditCardEntity(id, "log", "pass", new BigDecimal(10000)
                , new BigDecimal(1000), 3);
    }

    private Optional<DepositOrWithdrawalCalculatorTransactionResponse> transactionConvert(
            AddTransactionRequest request) {
        DepositOrWithdrawalCalculatorTransactionResponse response =
                new DepositOrWithdrawalCalculatorTransactionResponse(request.getAmount()
                        , request.getTransactionType(), request.getBetweenWhomTheTransaction(), request.getTransactionSuccess(),
                        3);

        return Optional.of(response);
    }

}