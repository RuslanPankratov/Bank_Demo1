package bank.core.service.calculator;


import bank.core.calculator.InsuranceCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.InsuranceEntity;
import bank.dto.insurance.calculator.InsuranceCalculatorRequest;
import bank.dto.insurance.calculator.InsuranceCalculatorResponse;
import bank.dto.transaction.AddTransactionRequest;
import bank.enum_class.TypeInsurance;
import bank.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class InsuranceCalculatorService {

    private final InsuranceRepository repository;
    private final AddTransactionService addTransactionService;

    @Transactional
    public Optional<InsuranceCalculatorResponse> calculate(InsuranceCalculatorRequest request) {

        log.debug("Received Insurance Calculator request: {}", request);

        Optional<InsuranceEntity> entity = repository.findByIdUser(request.getIdUser());
        InsuranceCalculatorResponse insuranceCalculatorResponse = new InsuranceCalculatorResponse();
        Optional<InsuranceCalculatorResponse> insuranceCalculatorResponseOptional =
                Optional.of(insuranceCalculatorResponse);

        log.debug("Received Insurance Entity request: {}", entity);
        log.debug("Received InsuranceCalculatorResponse request: {}", insuranceCalculatorResponseOptional);

        if (entity.isPresent()) {
            InsuranceCalculator insuranceCalculator = new InsuranceCalculator();
            InsuranceEntity insuranceEntity = entity.get();

            log.debug("Received Insurance Entity request: {}", insuranceEntity);

            AddTransactionRequest addTransactionRequest = insuranceCalculator.insurance(insuranceEntity,
                    request.getSum(), request.getTypeInsurance());

            addTransactionService.transaction(addTransactionRequest);
            repository.save(insuranceEntity);

            insuranceCalculatorResponseOptional = convert(insuranceEntity, request.getTypeInsurance());

            log.debug("Changed Insurance Entity request: {}", insuranceEntity);
            log.debug("Changed Add Transaction request: {}", addTransactionRequest);
            log.debug("Changed Insurance Calculator Response request: {}", insuranceCalculatorResponseOptional);

        }
        return insuranceCalculatorResponseOptional;
    }

    private Optional<InsuranceCalculatorResponse> convert(InsuranceEntity insurance, TypeInsurance typeInsurance) {
        return Optional.of(new InsuranceCalculatorResponse(insurance.getSumInsured(), insurance.getInsurancePaid(),
                insurance.getIdInsurance(), insurance.getIdUser(), typeInsurance));
    }
}
