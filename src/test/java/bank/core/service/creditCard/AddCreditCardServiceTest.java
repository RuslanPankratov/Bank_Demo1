package bank.core.service.creditCard;

import bank.domain.CreditCardEntity;
import bank.dto.creditCard.add.AddCreditCardRequest;
import bank.dto.creditCard.add.AddCreditCardResponse;
import bank.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddCreditCardServiceTest {
    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private AddCreditCardService addCreditCardService;


    @Test
    void addTest() {

        when(creditCardRepository.save(entity(null))).thenReturn(entity(133));

        AddCreditCardRequest request = request();

        AddCreditCardResponse result = addCreditCardService.add(request);
        AddCreditCardResponse expectedResult = new AddCreditCardResponse(133);

        assertEquals(expectedResult, result);

        verify(creditCardRepository).save(any());

        verifyNoMoreInteractions(creditCardRepository);
    }


    private AddCreditCardRequest request() {
        return new AddCreditCardRequest("log", "pass", new BigDecimal(10000)
                , new BigDecimal(1000), 133);
    }

    private CreditCardEntity entity(Integer id) {
        return new CreditCardEntity(id, "log", "pass", new BigDecimal(10000)
                , new BigDecimal(1000), 133);
    }


}