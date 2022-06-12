package bank.core.service.credit;

import bank.domain.CreditEntity;
import bank.core.service.credit.dto.credit.add.AddCreditRequest;
import bank.core.service.credit.dto.credit.add.AddCreditResponse;
import bank.repository.CreditRepository;
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
class AddCreditServiceTest {

    @Mock
    private CreditRepository creditRepository;

    @InjectMocks
    private AddCreditService addCreditService;


    @Test
    void addTest() {
        when(creditRepository.save(any())).thenReturn(entity());

        AddCreditRequest request = request();

        AddCreditResponse result = addCreditService.add(request);
        AddCreditResponse expectedResult = new AddCreditResponse(133);

        assertEquals(expectedResult, result);

        verify(creditRepository).save(any());

        verifyNoMoreInteractions(creditRepository);
    }


    private AddCreditRequest request() {
        return new AddCreditRequest(133, new BigDecimal(11790.95), new BigDecimal(2.15)
                , new BigDecimal(0)
                , new BigDecimal(11790), new BigDecimal(100), new BigDecimal(1790)
                , new BigDecimal(10000), new BigDecimal(117.91), 13);
    }

    private CreditEntity entity() {
        return new CreditEntity(133, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), 13);
    }

}