package bank.core.service.insurance;


import bank.domain.InsuranceEntity;
import bank.dto.insurance.update.UpdateInsuranceRequest;
import bank.dto.insurance.update.UpdateInsuranceResponse;
import bank.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class UpdateInsuranceService {

    private final InsuranceRepository repository;

    @Transactional
    public UpdateInsuranceResponse update(UpdateInsuranceRequest request) {

        repository.findById(request.getIdInsurance())
                .map(insurance -> updateFields(insurance, request))
                .ifPresent(repository::save);

        Optional<InsuranceEntity> insuranceEntity = repository.findById(request.getIdInsurance());

        log.debug("Return Update Insurance Response: {}", insuranceEntity);

        return convert(insuranceEntity);
    }


    private InsuranceEntity updateFields(InsuranceEntity entity, UpdateInsuranceRequest request) {
        entity.setSumInsured(request.getSumInsured());
        entity.setInsurancePaid(request.getInsurancePaid());
        entity.setIdUser(request.getIdUser());
        entity.setIdInsurance(request.getIdInsurance());

        log.debug("Update Insurance Entity: {}", entity);

        return entity;
    }


    private UpdateInsuranceResponse convert(Optional<InsuranceEntity> entityOptional) {
        UpdateInsuranceResponse updateInsuranceResponse = new UpdateInsuranceResponse();

        if (entityOptional.isPresent()) {
            InsuranceEntity entity = entityOptional.get();
            updateInsuranceResponse = new UpdateInsuranceResponse(entity.getSumInsured(), entity.getInsurancePaid()
                    , entity.getIdInsurance(), entity.getIdUser());
        }
        return updateInsuranceResponse;
    }

}
