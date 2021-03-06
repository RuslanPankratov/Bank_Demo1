package bank.core.service.calculator;

import bank.core.calculator.PayForInsurance;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.InsuranceEntity;
import bank.dto.insurance.pay.InsurancePayTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.WithWhomTheDeal;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.repository.CreditCardRepository;
import bank.repository.InsuranceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InsurancePayServiceTest {

    @Mock
    private InsuranceRepository insuranceRepository;
    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private AddTransactionService addTransactionService;
    @Mock
    private PayForInsurance payForInsurance;
    @InjectMocks
    private InsurancePayService insurancePayService;


    @Test
    void payTest() {
        AddTransactionRequest request = requestConvert();
        Optional<InsuranceEntity> insurance = Optional.of(convertInsuranceEntity());
        Optional<CreditCardEntity> creditCard = Optional.of(convertCreditCardEntity());

        when(payForInsurance.payInsurance(any(), any())).thenReturn(request);
        when(insuranceRepository.findById(any())).thenReturn(insurance);
        when(creditCardRepository.findById(any())).thenReturn(creditCard);

        InsurancePayTransactionResponse result = insurancePayService.pay(3,3);
        InsurancePayTransactionResponse expectedResult = transactionConvert(request);

        assertEquals(expectedResult, result);

        verify(insuranceRepository).findById(any());
        verify(insuranceRepository).save(any());
        verify(creditCardRepository).findById(any());
        verify(creditCardRepository).save(any());
        verify(addTransactionService).save(any());
        verify(payForInsurance).payInsurance(any(), any());

        verifyNoMoreInteractions(insuranceRepository, creditCardRepository, addTransactionService, payForInsurance);
    }


    private CreditCardEntity convertCreditCardEntity() {
        return new CreditCardEntity(3, "log", "pass", new BigDecimal(5000)
                , new BigDecimal(3000), 3);
    }

    private InsuranceEntity convertInsuranceEntity() {
        return new InsuranceEntity(12, new BigDecimal(1000), new BigDecimal(100), 3);
    }

    private AddTransactionRequest requestConvert() {
        return new AddTransactionRequest(new BigDecimal(1000), TransactionType.DEPOSIT
                , WithWhomTheDeal.INSIDE, TransactionSuccess.SUCCESSFUL, 3);
    }

    private InsurancePayTransactionResponse transactionConvert(AddTransactionRequest request) {
        return new InsurancePayTransactionResponse(request.getAmount()
                , request.getTransactionType(), request.getWithWhomTheDeal()
                , request.getTransactionSuccess(), 3);

    }
}