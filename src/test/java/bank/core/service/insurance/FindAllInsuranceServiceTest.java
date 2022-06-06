package bank.core.service.insurance;

import bank.domain.InsuranceEntity;
import bank.dto.insurance.InsuranceDTO;
import bank.dto.insurance.find.FindAllInsuranceResponse;
import bank.repository.InsuranceRepository;
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
class FindAllInsuranceServiceTest {

    @Mock
    private InsuranceRepository repository;

    @InjectMocks
    private FindAllInsuranceService findAllInsuranceService;

    @Test
    void findTest() {

        List<InsuranceEntity> insuranceEntities = insuranceConvertList();
        when(repository.findAll()).thenReturn(insuranceEntities);

        FindAllInsuranceResponse result = findAllInsuranceService.findAll();
        FindAllInsuranceResponse expectedResult = new FindAllInsuranceResponse(insuranceConvertListDTO(
                insuranceConvertList()));

        assertEquals(expectedResult, result);

        verify(repository).findAll();

        verifyNoMoreInteractions(repository);
    }


    private List<InsuranceEntity> insuranceConvertList() {
        List<InsuranceEntity> insuranceEntities = new ArrayList<>();
        InsuranceEntity insurance = convertEntity(1);
        InsuranceEntity insuranceTwo = convertEntity(2);
        InsuranceEntity insuranceThree = convertEntity(3);
        InsuranceEntity insuranceFour = convertEntity(4);
        insuranceEntities.add(insurance);
        insuranceEntities.add(insuranceTwo);
        insuranceEntities.add(insuranceThree);
        insuranceEntities.add(insuranceFour);

        return insuranceEntities;
    }

    private List<InsuranceDTO> insuranceConvertListDTO(List<InsuranceEntity> entities) {
        List<InsuranceDTO> insuranceDTOS = new ArrayList<>();
        insuranceDTOS.add(convertDTO(entities.get(0)));
        insuranceDTOS.add(convertDTO(entities.get(1)));
        insuranceDTOS.add(convertDTO(entities.get(2)));
        insuranceDTOS.add(convertDTO(entities.get(3)));

        return insuranceDTOS;
    }

    private InsuranceEntity convertEntity(Integer id) {
        return new InsuranceEntity(1, new BigDecimal(1000), new BigDecimal(100), id);
    }

    private InsuranceDTO convertDTO(InsuranceEntity entity) {
        return new InsuranceDTO(entity.getSumInsured(), entity.getInsurancePaid(), entity.getIdInsurance()
                , entity.getIdUser());
    }
}