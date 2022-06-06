package bank.core.service.calculator;

import bank.core.calculator.PayForInsurance;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.InsuranceEntity;
import bank.dto.insurance.pay.InsurancePayTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
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
    private final PayForInsurance payForInsurance;

    public Optional<InsurancePayTransactionResponse> pay(Integer userId) {

        log.debug("Received User Id request: {}", userId);

        Optional<InsuranceEntity> insurance = insuranceRepository.findByIdUser(userId);
        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(userId);

        InsurancePayTransactionResponse response = new InsurancePayTransactionResponse();
        Optional<InsurancePayTransactionResponse> responseOptional = Optional.of(response);

        log.debug("Received Insurance Entity request: {}", insurance);
        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Insurance Pay Response Transaction Optional request: {}", responseOptional);

        if (insurance.isPresent() && creditCard.isPresent()) {
            InsuranceEntity insuranceEntity = insurance.get();
            CreditCardEntity creditCardEntity = creditCard.get();


            log.debug("Received Insurance Entity request: {}", insuranceEntity);
            log.debug("Received Credit Card Entity request: {}", creditCard);

            AddTransactionRequest addTransactionRequest = payForInsurance.payInsurance(insuranceEntity,
                    creditCardEntity);

            addTransactionService.transaction(addTransactionRequest);
            insuranceRepository.save(insuranceEntity);
            creditCardRepository.save(creditCardEntity);

            responseOptional = convert(addTransactionRequest);

            log.debug("Changed Insurance Entity request: {}", insuranceEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            log.debug("Changed Insurance Pay Response Optional request: {}", responseOptional);
            log.debug("Changed Add Transaction request: {}", addTransactionRequest);

        }
        return responseOptional;
    }

    private Optional<InsurancePayTransactionResponse> convert(AddTransactionRequest request) {
        InsurancePayTransactionResponse response =
                new InsurancePayTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getBetweenWhomTheTransaction()
                        , request.getTransactionSuccess(), request.getIdUser());

        return Optional.of(response);
    }
}
