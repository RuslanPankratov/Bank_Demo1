package bank.core.service.insurance;


import bank.domain.InsuranceEntity;
import bank.dto.insurance.UpdateInsuranceRequest;
import bank.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Slf4j
@AllArgsConstructor
@Service
public class UpdateInsuranceService {


    private final InsuranceRepository repository;

    @Transactional
    public void update(UpdateInsuranceRequest request){
        repository.findById(request.getIdInsurance())
                .map(user -> updateFields(user, request))
                .ifPresent(repository::save);
    }


    private InsuranceEntity updateFields(InsuranceEntity entity, UpdateInsuranceRequest request){
        entity.setSumInsured(request.getSumInsured());
        entity.setInsurancePaid(request.getInsurancePaid());

        log.debug("Update Insurance Entity: {}", entity);

        return entity;
    }
}
