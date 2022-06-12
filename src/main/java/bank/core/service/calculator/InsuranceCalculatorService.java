package bank.core.service.calculator;


import bank.core.calculator.InsuranceCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.InsuranceEntity;
import bank.core.service.credit.dto.insurance.calculator.InsuranceCalculatorRequest;
import bank.core.service.credit.dto.insurance.calculator.InsuranceCalculatorTransactionResponse;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
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
    private final InsuranceCalculator insuranceCalculator;

    @Transactional
    public Optional<InsuranceCalculatorTransactionResponse> calculate(InsuranceCalculatorRequest request
            , Integer idUSer) {

        log.debug("Received Insurance Calculator request: {}", request);

        Optional<InsuranceEntity> entity = repository.findByIdUser(idUSer);
        InsuranceCalculatorTransactionResponse response = new InsuranceCalculatorTransactionResponse();
        Optional<InsuranceCalculatorTransactionResponse> responseOptional =
                Optional.of(response);

        log.debug("Received Insurance Entity request: {}", entity);
        log.debug("Received Insurance Calculator Transaction Response request: {}", responseOptional);

        if (entity.isPresent()) {
            responseOptional = convertResponse(entity, request);
        }

        return responseOptional;
    }

    private Optional<InsuranceCalculatorTransactionResponse> convert(AddTransactionRequest request) {
        InsuranceCalculatorTransactionResponse response =
                new InsuranceCalculatorTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), request.getIdUser());

        return Optional.of(response);
    }


    private Optional<InsuranceCalculatorTransactionResponse> convertResponse(Optional<InsuranceEntity> entity
            , InsuranceCalculatorRequest request) {
        InsuranceEntity insuranceEntity = entity.get();

        AddTransactionRequest addTransactionRequest = insuranceCalculator.insuranceCalculate(insuranceEntity,
                request.getSum(), request.getTypeInsurance());

        addTransactionService.save(addTransactionRequest);
        repository.save(insuranceEntity);

        Optional<InsuranceCalculatorTransactionResponse> responseOptional = convert(addTransactionRequest);

        log.debug("Changed Insurance Entity request: {}", insuranceEntity);
        log.debug("Changed Add Transaction request: {}", addTransactionRequest);
        log.debug("Changed Insurance Calculator Transaction Response request: {}", responseOptional);

        return responseOptional;
    }
}
