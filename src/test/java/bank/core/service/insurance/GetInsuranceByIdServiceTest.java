package bank.core.service.insurance;

import bank.domain.InsuranceEntity;
import bank.dto.insurance.InsuranceDTO;
import bank.dto.insurance.find.GetByIdInsuranceResponse;
import bank.repository.InsuranceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetInsuranceByIdServiceTest {

    @Mock
    private InsuranceRepository repository;

    @InjectMocks
    private GetInsuranceByIdService getInsuranceByIdService;

    @Test
    void findTest() {

        when(repository.findById(1)).thenReturn(Optional.of(convertEntity(1)));

        GetByIdInsuranceResponse result = getInsuranceByIdService.getInsuranceById(1);
        GetByIdInsuranceResponse expectedResult = new GetByIdInsuranceResponse(convertDTO(convertEntity(1)));

        assertEquals(expectedResult, result);

        verify(repository).findById(anyInt());

        verifyNoMoreInteractions(repository);

    }


    private InsuranceEntity convertEntity(Integer id) {
        return new InsuranceEntity(1, new BigDecimal(1000), new BigDecimal(100), id);
    }


    private InsuranceDTO convertDTO(InsuranceEntity entity) {
        return new InsuranceDTO(entity.getSumInsured(), entity.getInsurancePaid(), entity.getIdInsurance()
                , entity.getIdUser());
    }

}