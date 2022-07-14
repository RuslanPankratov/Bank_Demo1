package bank.core.service.creditCard;

import bank.domain.CreditCardEntity;
import bank.dto.creditCard.update.UpdateCreditCardRequest;
import bank.dto.creditCard.update.UpdateCreditCardResponse;
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
class UpdateCreditCardServiceTest {

    @Mock
    private CreditCardRepository repository;

    @InjectMocks
    private UpdateCreditCardService updateCreditCardService;

    @Test
    void updateTest() {

        when(repository.findById(12)).thenReturn(Optional.of(entity(12)));

        UpdateCreditCardRequest updateCreditCardRequest = convertRequest(12);

        UpdateCreditCardResponse result = updateCreditCardService.update(updateCreditCardRequest);
        UpdateCreditCardResponse expectedResult = convertResponse(12);

        assertEquals(expectedResult, result);

        verify(repository).save(any());
        verify(repository, times(2)).findById(any());

        verifyNoMoreInteractions(repository);

    }

    private UpdateCreditCardRequest convertRequest(Integer id) {
        return new UpdateCreditCardRequest("log", "pass", new BigDecimal(2000)
                , new BigDecimal(400), id, 150);

    }


    private CreditCardEntity entity(Integer id) {
        return new CreditCardEntity(id, "log", "pass", new BigDecimal(2000)
                , new BigDecimal(400), 150);
    }

    private UpdateCreditCardResponse convertResponse(Integer id) {
        return new UpdateCreditCardResponse("log", "pass", new BigDecimal(2000)
                , new BigDecimal(400), id, 150);

    }
}