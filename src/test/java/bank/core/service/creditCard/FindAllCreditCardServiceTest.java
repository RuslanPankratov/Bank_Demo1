package bank.core.service.creditCard;

import bank.domain.CreditCardEntity;
import bank.dto.creditCard.CreditCardDTO;
import bank.dto.creditCard.find.FindAllCreditCardResponse;
import bank.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAllCreditCardServiceTest {
    @Mock
    private CreditCardRepository repository;

    @InjectMocks
    private FindAllCreditCardService findAllCreditCardService;

    @Test
    void findTest() {

        List<CreditCardEntity> creditCardEntityList = convertCreditCardEntityList();
        when(repository.findAll()).thenReturn(creditCardEntityList);

        FindAllCreditCardResponse result = findAllCreditCardService.findAll();
        FindAllCreditCardResponse expectedResult = new FindAllCreditCardResponse(
                creditCardDTOSConvert(creditCardEntityList));

        assertEquals(expectedResult,result);

        verify(repository).findAll();

        verifyNoMoreInteractions(repository);

    }


    private CreditCardEntity entity(Integer id) {
        return new CreditCardEntity(id, "log", "pass", new BigDecimal(10000)
                , new BigDecimal(1000), 133);
    }


    private List<CreditCardEntity> convertCreditCardEntityList() {
        List<CreditCardEntity> creditCardEntityList = new ArrayList<>();
        creditCardEntityList.add(entity(1));
        creditCardEntityList.add(entity(2));
        creditCardEntityList.add(entity(3));
        creditCardEntityList.add(entity(4));

        return creditCardEntityList;
    }

    private List<CreditCardDTO> creditCardDTOSConvert(List<CreditCardEntity> entities) {
        List<CreditCardDTO> creditCardDTOS = new ArrayList<>();
        creditCardDTOS.add(creditCardDTOConvert(entities.get(0)));
        creditCardDTOS.add(creditCardDTOConvert(entities.get(1)));
        creditCardDTOS.add(creditCardDTOConvert(entities.get(2)));
        creditCardDTOS.add(creditCardDTOConvert(entities.get(3)));

        return creditCardDTOS;
    }

    private CreditCardDTO creditCardDTOConvert(CreditCardEntity entity) {
        return new CreditCardDTO(entity.getLogin(), entity.getPassword(), entity.getInvoiceAmount()
                , entity.getWithdrawalLimit(), entity.getIdCreditCard(), entity.getIdUser());
    }

}