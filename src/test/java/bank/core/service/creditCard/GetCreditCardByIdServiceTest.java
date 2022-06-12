package bank.core.service.creditCard;

import bank.domain.CreditCardEntity;
import bank.core.service.credit.dto.creditCard.CreditCardDTO;
import bank.core.service.credit.dto.creditCard.find.GetByIdCreditCardResponse;
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
class GetCreditCardByIdServiceTest {

    @Mock
    private CreditCardRepository repository;

    @InjectMocks
    private GetCreditCardByIdService getCreditCardByIdService;

    @Test
    void findTest() {

        when(repository.findById(140)).thenReturn(Optional.of(entity(140)));

        GetByIdCreditCardResponse result = getCreditCardByIdService.getCreditCardById(140);
        GetByIdCreditCardResponse expectedResult = new GetByIdCreditCardResponse(creditCardDTOConvert(entity(140)));

        assertEquals(expectedResult, result);

        verify(repository).findById(any());

        verifyNoMoreInteractions(repository);
    }


    private CreditCardEntity entity(Integer id) {
        return new CreditCardEntity(id, "log", "pass", new BigDecimal(10000)
                , new BigDecimal(1000), 133);
    }


    private CreditCardDTO creditCardDTOConvert(CreditCardEntity entity) {
        return new CreditCardDTO(entity.getLogin(), entity.getPassword(), entity.getInvoiceAmount()
                , entity.getWithdrawalLimit(), entity.getIdCreditCard(), entity.getIdUser());
    }

}