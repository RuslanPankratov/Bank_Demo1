package bank.core.service.calculator;

import bank.core.calculator.PayForInsurance;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.InsuranceEntity;
import bank.dto.insurance.pay.InsurancePayResponse;
import bank.dto.transaction.AddTransactionRequest;
import bank.repository.CreditCardRepository;
import bank.repository.InsuranceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class InsurancePayService {

    private final InsuranceRepository insuranceRepository;
    private final CreditCardRepository creditCardRepository;

    private final AddTransactionService addTransactionService;

    public Optional<InsurancePayResponse> pay(Integer userId) {

        log.debug("Received User Id request: {}", userId);

        Optional<InsuranceEntity> insurance = insuranceRepository.findByIdUser(userId);
        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(userId);
        InsurancePayResponse insurancePayResponse = new InsurancePayResponse();
        Optional<InsurancePayResponse> insurancePayResponseOptional = Optional.of(insurancePayResponse);

        log.debug("Received Insurance Entity request: {}", insurance);
        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Insurance Pay Response Optional request: {}", insurancePayResponseOptional);

        if (insurance.isPresent() && creditCard.isPresent()) {
            InsuranceEntity insuranceEntity = insurance.get();
            CreditCardEntity creditCardEntity = creditCard.get();
            PayForInsurance payForInsurance = new PayForInsurance();

            log.debug("Received Insurance Entity request: {}", insuranceEntity);
            log.debug("Received Credit Card Entity request: {}", creditCard);

            AddTransactionRequest addTransactionRequest = payForInsurance.payInsurance(insuranceEntity,
                    creditCardEntity);

            addTransactionService.transaction(addTransactionRequest);
            insuranceRepository.save(insuranceEntity);
            creditCardRepository.save(creditCardEntity);

            insurancePayResponseOptional = convert(insuranceEntity);

            log.debug("Changed Insurance Entity request: {}", insuranceEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            log.debug("Changed Insurance Pay Response Optional request: {}", insurancePayResponseOptional);
            log.debug("Changed Add Transaction request: {}", addTransactionRequest);

        }
        return insurancePayResponseOptional;
    }

    private Optional<InsurancePayResponse> convert(InsuranceEntity entity) {
        return Optional.of(new InsurancePayResponse(entity.getSumInsured(), entity.getInsurancePaid(),
                entity.getIdInsurance(), entity.getIdUser()));
    }
}
