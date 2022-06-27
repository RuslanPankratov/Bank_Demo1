package bank.core.service.credit;

import bank.domain.CreditEntity;
import bank.dto.credit.CreditDTO;
import bank.dto.credit.find.FindAllCreditResponse;
import bank.repository.CreditRepository;
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
class FindAllCreditServiceTest {

    @Mock
    private CreditRepository repository;

    @InjectMocks
    private FindAllCreditService findAllCreditService;

    @Test
    void findTest() {
        List<CreditEntity> creditEntityList = convertCreditEntityList();
        when(repository.findAll()).thenReturn(creditEntityList);

        FindAllCreditResponse result = findAllCreditService.findAll();
        FindAllCreditResponse expectedResult = new FindAllCreditResponse(
                creditDTOSConvert(creditEntityList));

        assertEquals(expectedResult, result);

        verify(repository).findAll();

        verifyNoMoreInteractions(repository);
    }


    private CreditEntity entity(Integer id) {
        return new CreditEntity(133, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), 13);
    }


    private List<CreditEntity> convertCreditEntityList() {
        List<CreditEntity> creditCardEntityList = new ArrayList<>();
        creditCardEntityList.add(entity(1));
        creditCardEntityList.add(entity(2));
        creditCardEntityList.add(entity(3));
        creditCardEntityList.add(entity(4));

        return creditCardEntityList;
    }

    private List<CreditDTO> creditDTOSConvert(List<CreditEntity> entities) {
        List<CreditDTO> creditDTOS = new ArrayList<>();
        creditDTOS.add(creditDTOConvert(entities.get(0)));
        creditDTOS.add(creditDTOConvert(entities.get(1)));
        creditDTOS.add(creditDTOConvert(entities.get(2)));
        creditDTOS.add(creditDTOConvert(entities.get(3)));

        return creditDTOS;
    }

    private CreditDTO creditDTOConvert(CreditEntity entity) {
        return new CreditDTO(entity.getIdCredit(), entity.getHowMuchToPay()
                , entity.getPercentRate(), entity.getPaid(), entity.getTheTotalAmountYouPay()
                , entity.getCountMonthsToPay(), entity.getBankProfit(), entity.getHowMuchIsTheLoan()
                , entity.getPaymentPerMonth(), entity.getIdUser());
    }

}