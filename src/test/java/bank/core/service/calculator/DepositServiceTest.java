package bank.core.service.calculator;

import bank.core.calculator.DepositCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.core.service.credit.dto.transaction.DepositCalculatorTransactionResponse;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.WithWhomTheDeal;
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
class DepositServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private AddTransactionService addTransactionService;
    @Mock
    private DepositCalculator depositCalculator;
    @InjectMocks
    private DepositService depositService;

    @Test
    void depositTest() {

        AddTransactionRequest request = requestConvert();
        CreditCardEntity creditCardEntity = creditCardConvert(3);

        when(creditCardRepository.findByIdUser(any())).thenReturn(Optional.of(creditCardEntity));
        when(depositCalculator.depositCalculator(any(), any())).thenReturn(request);

        Optional<DepositCalculatorTransactionResponse> result
                = depositService.deposit(request,request.getIdUser());
        Optional<DepositCalculatorTransactionResponse> expectedResult = transactionConvert(request);

        assertEquals(expectedResult, result);

        verify(creditCardRepository).findByIdUser(any());
        verify(creditCardRepository).save(any());
        verify(addTransactionService).save(any());
        verify(depositCalculator).depositCalculator(any(), any());


        verifyNoMoreInteractions(creditCardRepository, addTransactionService, depositCalculator);
    }


    @Test
    void withdrawalTest() {

        AddTransactionRequest request = requestConvert();
        request.setTransactionType(TransactionType.WITHDRAWAL);
        CreditCardEntity creditCardEntity = creditCardConvert(3);

        when(creditCardRepository.findByIdUser(any())).thenReturn(Optional.of(creditCardEntity));
        when(depositCalculator.depositCalculator(any(), any())).thenReturn(request);

        Optional<DepositCalculatorTransactionResponse> result =
                depositService.deposit(request,request.getIdUser());
        Optional<DepositCalculatorTransactionResponse> expectedResult = transactionConvert(request);

        assertEquals(expectedResult, result);

        verify(creditCardRepository).findByIdUser(any());
        verify(creditCardRepository).save(any());
        verify(addTransactionService).save(any());
        verify(depositCalculator).depositCalculator(any(), any());


        verifyNoMoreInteractions(creditCardRepository, addTransactionService, depositCalculator);
    }

    private AddTransactionRequest requestConvert() {
        return new AddTransactionRequest(new BigDecimal(1000), TransactionType.DEPOSIT
                , WithWhomTheDeal.INSIDE, TransactionSuccess.SUCCESSFUL, 3);
    }

    private CreditCardEntity creditCardConvert(Integer id) {
        return new CreditCardEntity(id, "log", "pass", new BigDecimal(10000)
                , new BigDecimal(1000), 3);
    }

    private Optional<DepositCalculatorTransactionResponse> transactionConvert(
            AddTransactionRequest request) {
        DepositCalculatorTransactionResponse response =
                new DepositCalculatorTransactionResponse(request.getAmount()
                        , request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), 3);

        return Optional.of(response);
    }

}