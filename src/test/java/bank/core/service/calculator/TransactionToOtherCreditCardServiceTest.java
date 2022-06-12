package bank.core.service.calculator;

import bank.core.calculator.TransactionToOtherCreditCard;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.core.service.credit.dto.creditCard.CreditCardToOtherRequest;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
import bank.core.service.credit.dto.transaction.add.AddTransactionResponse;
import bank.core.service.credit.dto.transaction.add.ListAddTransactionResponse;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionToOtherCreditCardServiceTest {

    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private AddTransactionService addTransactionService;
    @Mock
    private TransactionToOtherCreditCard transactionToOtherCreditCard;
    @InjectMocks
    private TransactionToOtherCreditCardService transactionToOtherCreditCardService;

    @Test
    void transactionTest() {

        CreditCardToOtherRequest request = request();
        Optional<CreditCardEntity> creditCardSender = Optional.of(creditCardConvert(3));
        Optional<CreditCardEntity> creditCardRecipient = Optional.of(creditCardConvert(2));

        when(creditCardRepository.findByIdUser(2)).thenReturn(creditCardSender);
        when(creditCardRepository.findByIdUser(3)).thenReturn(creditCardRecipient);
        when(transactionToOtherCreditCard.transaction(any(), any(), any())).thenReturn(requestListConvert());

        Optional<ListAddTransactionResponse> result = transactionToOtherCreditCardService.transaction(request
                , 3, 2);
        Optional<ListAddTransactionResponse> expectedResult = convertList(requestListConvert());

        assertEquals(expectedResult, result);

        verify(creditCardRepository, times(2)).findByIdUser(any());
        verify(creditCardRepository, times(2)).save(any());
        verify(addTransactionService, times(2)).save(any());
        verify(transactionToOtherCreditCard).transaction(any(), any(), any());

        verifyNoMoreInteractions(creditCardRepository, addTransactionService, transactionToOtherCreditCard);

    }


    private List<AddTransactionRequest> requestListConvert() {
        List<AddTransactionRequest> requests = new ArrayList<>();
        requests.add(requestConvert(TransactionType.SENDING, 3));
        requests.add(requestConvert(TransactionType.RECEIVING, 2));
        return requests;
    }

    private AddTransactionRequest requestConvert(TransactionType transactionType, Integer id) {
        return new AddTransactionRequest(new BigDecimal(2000), transactionType
                , WithWhomTheDeal.OTHER_PEOPLE, TransactionSuccess.SUCCESSFUL, id);
    }

    private Optional<ListAddTransactionResponse> convertList(List<AddTransactionRequest> request) {
        List<AddTransactionResponse> addTransactionResponses = new ArrayList<>();

        addTransactionResponses.add(convert(request.get(0)));
        addTransactionResponses.add(convert(request.get(1)));

        return Optional.of(new ListAddTransactionResponse(addTransactionResponses));
    }

    private AddTransactionResponse convert(AddTransactionRequest request) {
        return new AddTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getWithWhomTheDeal(), request.getTransactionSuccess(),
                request.getIdUser());
    }

    private CreditCardEntity creditCardConvert(Integer id) {
        return new CreditCardEntity(3, "log", "pass", new BigDecimal(10000)
                , new BigDecimal(3000), id);
    }

    private CreditCardToOtherRequest request() {
        return new CreditCardToOtherRequest(new BigDecimal(2000));
    }

}