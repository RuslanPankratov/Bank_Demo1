package bank.core.service.credit;

import bank.domain.CreditEntity;
import bank.core.service.credit.dto.credit.CreditDTO;
import bank.core.service.credit.dto.credit.find.GetByIdCreditResponse;
import bank.repository.CreditRepository;
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
class GetCreditByIdServiceTest {

    @Mock
    private CreditRepository repository;

    @InjectMocks
    private GetCreditByIdService getCreditByIdService;

    @Test
    void findTest(){
        when(repository.findById(any())).thenReturn(Optional.of(entity()));

        GetByIdCreditResponse result = getCreditByIdService.getCreditById(140);
        GetByIdCreditResponse expectedResult = new GetByIdCreditResponse(creditDTOConvert(entity()));

        assertEquals(expectedResult,result);

        verify(repository).findById(any());

        verifyNoMoreInteractions(repository);
    }


    private CreditEntity entity(){
        return new CreditEntity(133, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), 13);
    }


    private CreditDTO creditDTOConvert(CreditEntity entity) {
        return new CreditDTO(entity.getIdCredit(), entity.getHowMuchToPay()
                , entity.getPercentRate(), entity.getPaid(), entity.getTheTotalAmountYouPay()
                , entity.getCountMonthsToPay(), entity.getBankProfit(), entity.getHowMuchIsTheLoan()
                , entity.getPaymentPerMonth(), entity.getIdUser());
    }

}