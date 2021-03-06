package bank.core.service.insurance;


import bank.domain.InsuranceEntity;
import bank.dto.insurance.add.AddInsuranceRequest;
import bank.dto.insurance.add.AddInsuranceResponse;
import bank.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddInsuranceService {

    private InsuranceRepository repository;

    public AddInsuranceResponse add(AddInsuranceRequest request) {

        InsuranceEntity entity = convert(request);

        InsuranceEntity createdEntity = repository.save(entity);
        AddInsuranceResponse response = new AddInsuranceResponse();
        response.setCreatedInsuranceId(createdEntity.getIdInsurance());

        log.debug("Insurance Entity request: {}", entity);
        log.info("Successfully saved: {}", createdEntity);
        log.debug("Sending response: {}", response);

        return response;
    }

    private InsuranceEntity convert(AddInsuranceRequest request) {
        InsuranceEntity entity = new InsuranceEntity();
        entity.setInsurancePaid(request.getInsurancePaid());
        entity.setSumInsured(request.getSumInsured());
        entity.setIdUser(request.getIdUser());
        return entity;
    }
}
