package bank.core.service.calculator;

import bank.core.calculator.WithdrawalCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.dto.transaction.WithdrawalCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.repository.CreditCardRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Data
public class WithdrawalService {

    private final CreditCardRepository creditCardRepository;
    private final AddTransactionService addTransactionService;

    private final WithdrawalCalculator withdrawalCalculator;

    public WithdrawalCalculatorTransactionResponse withdrawal(
            AddTransactionRequest request, Integer idCreditCard) {

        log.debug("Received Add Transaction request: {}", request);

        Optional<CreditCardEntity> creditCard = creditCardRepository.findById(idCreditCard);
        WithdrawalCalculatorTransactionResponse response =
                new WithdrawalCalculatorTransactionResponse();


        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Add Transaction Response request: {}"
                , response);

        if (creditCard.isPresent()) {
            response = convertResponse(creditCard.get(), request, idCreditCard);
        }
        return response;
    }

    private WithdrawalCalculatorTransactionResponse convert(AddTransactionRequest request, Integer idCreditCard) {
        return new WithdrawalCalculatorTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), idCreditCard);
    }

    private WithdrawalCalculatorTransactionResponse convertResponse(CreditCardEntity creditCard
            , AddTransactionRequest request, Integer idCreditCard) {

        AddTransactionRequest addTransactionRequest = withdrawalCalculator.withdrawalCalculator(creditCard,
                request);

        addTransactionService.save(addTransactionRequest);
        creditCardRepository.save(creditCard);

        WithdrawalCalculatorTransactionResponse responseOptional = convert(addTransactionRequest, idCreditCard);

        log.debug("Changed Credit Card Entity request: {}", creditCard);
        log.debug("Changed Add Transaction Response request: {}", responseOptional);

        return responseOptional;
    }

}

