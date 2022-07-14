package bank.core.service.calculator;


import bank.core.calculator.InsuranceCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.InsuranceEntity;
import bank.dto.insurance.calculator.InsuranceCalculatorRequest;
import bank.dto.insurance.calculator.InsuranceCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
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
    public InsuranceCalculatorTransactionResponse calculate(InsuranceCalculatorRequest request
            , Integer idInsurance) {

        log.debug("Received Insurance Calculator request: {}", request);

        Optional<InsuranceEntity> entity = repository.findById(idInsurance);
        InsuranceCalculatorTransactionResponse response = new InsuranceCalculatorTransactionResponse();

        log.debug("Received Insurance Entity request: {}", entity);
        log.debug("Received Insurance Calculator Transaction Response request: {}", response);

        if (entity.isPresent()) {
            response = convertResponse(entity.get(), request);
        }

        return response;
    }

    private InsuranceCalculatorTransactionResponse convert(AddTransactionRequest request) {
           return new InsuranceCalculatorTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), request.getIdUser());
    }


    private InsuranceCalculatorTransactionResponse convertResponse(InsuranceEntity entity
            , InsuranceCalculatorRequest request) {


        AddTransactionRequest addTransactionRequest = insuranceCalculator.insuranceCalculate(entity,
                request.getSum(), request.getTypeInsurance());

        addTransactionService.save(addTransactionRequest);
        repository.save(entity);

        InsuranceCalculatorTransactionResponse response = convert(addTransactionRequest);

        log.debug("Changed Insurance Entity request: {}", entity);
        log.debug("Changed Add Transaction request: {}", addTransactionRequest);
        log.debug("Changed Insurance Calculator Transaction Response request: {}", response);

        return response;
    }
}
