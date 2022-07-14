package bank.core.service.insurance;

import bank.domain.InsuranceEntity;
import bank.dto.insurance.add.AddInsuranceRequest;
import bank.dto.insurance.add.AddInsuranceResponse;
import bank.repository.InsuranceRepository;
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
class AddInsuranceServiceTest {

    @Mock
    private InsuranceRepository repository;

    @InjectMocks
    private AddInsuranceService addInsuranceService;

    @Test
    void addTest(){

        when(repository.save(convertEntity(null))).thenReturn(convertEntity(12));

        AddInsuranceRequest request = convertRequest();

        AddInsuranceResponse result = addInsuranceService.add(request);
        AddInsuranceResponse expectedResult = new AddInsuranceResponse(12);

        assertEquals(expectedResult,result);

        verify(repository).save(any());


        verifyNoMoreInteractions(repository);
    }

    private InsuranceEntity convertEntity(Integer id) {
        return new InsuranceEntity(id, new BigDecimal(1000), new BigDecimal(100), 12);
    }

    private AddInsuranceRequest convertRequest() {
        return new AddInsuranceRequest(new BigDecimal(1000), new BigDecimal(100), 12);
    }

}