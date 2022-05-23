package bank.core.service.insurance;

import bank.domain.InsuranceEntity;
import bank.dto.insurance.GetByIdInsuranceResponse;
import bank.dto.insurance.InsuranceDTO;
import bank.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class GetInsuranceByIdService {

    private InsuranceRepository repository;

    public GetByIdInsuranceResponse getInsuranceById(Integer id) {
        GetByIdInsuranceResponse getByIdInsuranceResponse = repository.findById(id)
                .map(this::convert)
                .map(GetByIdInsuranceResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + "is not found."));

        log.debug("Return Get By Id Insurance Response: {}", getByIdInsuranceResponse);

        return getByIdInsuranceResponse;
    }

    private InsuranceDTO convert(InsuranceEntity entity) {
        return new InsuranceDTO(entity.getSumInsured(), entity.getInsurancePaid(), entity.getIdInsurance(),entity.getIdUser());
    }
}
