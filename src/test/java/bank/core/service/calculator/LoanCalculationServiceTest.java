package bank.core.service.calculator;

import bank.core.calculator.LoanCalculation;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.dto.credit.loan.CreditLoanCalculationTransactionResponse;
import bank.dto.credit.loan.CreditLoanRequest;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.WithWhomTheDeal;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.enum_class.TypeOfBenefits;
import bank.repository.CreditCardRepository;
import bank.repository.CreditRepository;
import bank.repository.UserRepository;
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
class LoanCalculationServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private AddTransactionService addTransactionService;
    @Mock
    private LoanCalculation loanCalculation;
    @InjectMocks
    private LoanCalculationService loanCalculationService;

    @Test
    void loanTest() {

        CreditLoanRequest request = convertRequest();
        Optional<CreditEntity> credit = Optional.of(creditEntityConvert());
        Optional<UserEntity> user = Optional.of(userConvert());
        Optional<CreditCardEntity> creditCard = Optional.of(convertCreditCardEntity());

        when(creditRepository.findById(any())).thenReturn(credit);
        when(userRepository.findById(any())).thenReturn(user);
        when(creditCardRepository.findById(any())).thenReturn(creditCard);
        when(loanCalculation.interestRateMethod(any(), any(), any(), any())).thenReturn(addTransactionRequestConvert());

        CreditLoanCalculationTransactionResponse result = loanCalculationService.loan(request, 3,3
                ,3);
        CreditLoanCalculationTransactionResponse expectedResult = convert(addTransactionRequestConvert());

        assertEquals(expectedResult, result);

        verify(userRepository).findById(any());
        verify(creditRepository).findById(any());
        verify(creditRepository).save(any());
        verify(creditCardRepository).findById(any());
        verify(creditCardRepository).save(any());
        verify(addTransactionService).save(any());
        verify(loanCalculation).interestRateMethod(any(), any(), any(), any());

        verifyNoMoreInteractions(userRepository, creditRepository, creditCardRepository, addTransactionService
                , loanCalculation);
    }

    private UserEntity userConvert() {
        UserEntity user = new UserEntity();
        user.setIdUser(1);
        user.setFirstName("Ruslan");
        user.setLastName("Pankratov");
        user.setAge(25);
        user.setTypeOfBenefits(TypeOfBenefits.THE_LARGE_FAMILY);

        return user;
    }

    private CreditLoanRequest convertRequest() {
        return new CreditLoanRequest(new BigDecimal(2), new BigDecimal(20000)
                , new BigDecimal(200));
    }

    private CreditEntity creditEntityConvert() {
        return new CreditEntity(2, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), 3);
    }

    private AddTransactionRequest addTransactionRequestConvert() {
        return new AddTransactionRequest(new BigDecimal(100)
                , TransactionType.PAY, WithWhomTheDeal.INSURANCE, TransactionSuccess.SUCCESSFUL, 1);
    }


    private CreditLoanCalculationTransactionResponse convert(AddTransactionRequest request) {
        return new CreditLoanCalculationTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getWithWhomTheDeal()
                , request.getTransactionSuccess(), request.getIdUser());

    }

    private CreditCardEntity convertCreditCardEntity() {
        return new CreditCardEntity(3, "log", "pass", new BigDecimal(5000)
                , new BigDecimal(3000), 3);
    }

}