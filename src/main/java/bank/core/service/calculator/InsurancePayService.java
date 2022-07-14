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

    public InsurancePayTransactionResponse pay(Integer idInsurance, Integer idCreditCard) {

        log.debug("Received Id Insurance request: {}", idInsurance);
        log.debug("Received Id Credit Card request: {}", idCreditCard);

        Optional<InsuranceEntity> insurance = insuranceRepository.findById(idInsurance);
        Optional<CreditCardEntity> creditCard = creditCardRepository.findById(idCreditCard);

        InsurancePayTransactionResponse response = new InsurancePayTransactionResponse();

        log.debug("Received Insurance Entity request: {}", insurance);
        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Insurance Pay Response Transaction  request: {}", response);

        if (insurance.isPresent() && creditCard.isPresent()) {
            response = convertResponse(insurance.get(), creditCard.get());
        }
        return response;
    }

    private InsurancePayTransactionResponse convert(AddTransactionRequest request) {
        return new InsurancePayTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getWithWhomTheDeal()
                , request.getTransactionSuccess(), request.getIdUser());
    }


    private InsurancePayTransactionResponse convertResponse(InsuranceEntity insurance
            , CreditCardEntity creditCard) {

        AddTransactionRequest addTransactionRequest = payForInsurance.payInsurance(insurance,
                creditCard);

        addTransactionService.save(addTransactionRequest);
        insuranceRepository.save(insurance);
        creditCardRepository.save(creditCard);

        InsurancePayTransactionResponse response = convert(addTransactionRequest);

        log.debug("Changed Insurance Entity request: {}", insurance);
        log.debug("Changed Credit Card Entity request: {}", creditCard);
        log.debug("Changed Insurance Pay Response request: {}", response);
        log.debug("Changed Add Transaction request: {}", addTransactionRequest);

        return response;
    }
}
