package bank.core.service.insurance;

import bank.domain.InsuranceEntity;
import bank.core.service.credit.dto.insurance.find.FindAllInsuranceResponse;
import bank.core.service.credit.dto.insurance.InsuranceDTO;
import bank.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class FindAllInsuranceService {

    private InsuranceRepository repository;

    public FindAllInsuranceResponse findAll() {

        List<InsuranceDTO> dtos = repository.findAll().stream()
                .map(this::convert)
                .toList();

        FindAllInsuranceResponse findAllInsuranceResponse = new FindAllInsuranceResponse(dtos);

        log.debug("Find All Credit Card: {}", dtos);
        log.debug("Find All Credit Card: {}", findAllInsuranceResponse);

        return findAllInsuranceResponse;
    }

    private InsuranceDTO convert(InsuranceEntity entity) {
        return new InsuranceDTO(entity.getSumInsured(), entity.getInsurancePaid(),
                entity.getIdInsurance(), entity.getIdUser());
    }
}
