package bank.core.service.calculator;

import bank.core.calculator.InsuranceCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.InsuranceEntity;
import bank.dto.insurance.calculator.InsuranceCalculatorRequest;
import bank.dto.insurance.calculator.InsuranceCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.enum_class.TypeInsurance;
import bank.repository.InsuranceRepository;
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
class InsuranceCalculatorServiceTest {

    @Mock
    private InsuranceRepository repository;
    @Mock
    private AddTransactionService addTransactionService;
    @Mock
    private InsuranceCalculator insuranceCalculator;
    @InjectMocks
    private InsuranceCalculatorService insuranceCalculatorService;

    @Test
    void calculateTest() {
        InsuranceCalculatorRequest request = requestConvert();
        InsuranceEntity insuranceEntity = insuranceConvert();
        AddTransactionRequest addTransactionRequest = requestTransactionConvert();

        when(repository.findByIdUser(any())).thenReturn(Optional.of(insuranceEntity));
        when(insuranceCalculator.insurance(any(), any(), any())).thenReturn(addTransactionRequest);

        Optional<InsuranceCalculatorTransactionResponse> result = insuranceCalculatorService.calculate(request);
        Optional<InsuranceCalculatorTransactionResponse> expectedResult = transactionConvert(addTransactionRequest);

        assertEquals(expectedResult, result);

        verify(repository).findByIdUser(any());
        verify(repository).save(any());
        verify(addTransactionService).transaction(any());
        verify(insuranceCalculator).insurance(any(), any(), any());

        verifyNoMoreInteractions(repository, addTransactionService, insuranceCalculator);

    }

    private InsuranceCalculatorRequest requestConvert() {
        return new InsuranceCalculatorRequest(3, new BigDecimal(2000), TypeInsurance.HEALTH);
    }

    private InsuranceEntity insuranceConvert() {
        return new InsuranceEntity(23, new BigDecimal(0), new BigDecimal(0), 3);
    }

    private AddTransactionRequest requestTransactionConvert() {
        return new AddTransactionRequest(new BigDecimal(1000), TransactionType.DEPOSIT
                , BetweenWhomTheTransaction.INSIDE, TransactionSuccess.NOT_ENOUGH_MONEY, 3);
    }

    private Optional<InsuranceCalculatorTransactionResponse> transactionConvert(
            AddTransactionRequest request) {
        InsuranceCalculatorTransactionResponse response =
                new InsuranceCalculatorTransactionResponse(request.getAmount()
                        , request.getTransactionType(), request.getBetweenWhomTheTransaction()
                        , request.getTransactionSuccess(), 3);

        return Optional.of(response);
    }
}