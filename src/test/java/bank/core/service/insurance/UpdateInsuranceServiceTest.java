package bank.core.service.insurance;

import bank.domain.InsuranceEntity;
import bank.dto.insurance.update.UpdateInsuranceRequest;
import bank.dto.insurance.update.UpdateInsuranceResponse;
import bank.repository.InsuranceRepository;
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
class UpdateInsuranceServiceTest {

    @Mock
    private InsuranceRepository insuranceRepository;

    @InjectMocks
    private UpdateInsuranceService updateInsuranceService;

    @Test
    void updateTest() {

        when(insuranceRepository.findById(any())).thenReturn(Optional.of(convertEntity()));

        UpdateInsuranceRequest request = convertRequest();

        UpdateInsuranceResponse result = updateInsuranceService.update(request);
        UpdateInsuranceResponse expectedResult = convertResponse();

        assertEquals(expectedResult, result);

        verify(insuranceRepository).save(any());
        verify(insuranceRepository, times(2)).findById(any());

        verifyNoMoreInteractions(insuranceRepository);
    }

    private InsuranceEntity convertEntity() {
        return new InsuranceEntity(1, new BigDecimal(1000), new BigDecimal(100), 12);
    }

    private UpdateInsuranceRequest convertRequest() {
        return new UpdateInsuranceRequest(new BigDecimal(1000), new BigDecimal(100), 1, 12);
    }

    private UpdateInsuranceResponse convertResponse() {
        return new UpdateInsuranceResponse(new BigDecimal(1000), new BigDecimal(100), 1, 12);
    }

}