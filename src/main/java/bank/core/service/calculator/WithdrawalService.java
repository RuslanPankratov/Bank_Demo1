package bank.core.service.calculator;

import bank.core.calculator.WithdrawalCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.core.service.credit.dto.transaction.WithdrawalCalculatorTransactionResponse;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
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

    public Optional<WithdrawalCalculatorTransactionResponse> withdrawal(
            AddTransactionRequest request, Integer idUser) {

        log.debug("Received Add Transaction request: {}", request);

        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(idUser);
        WithdrawalCalculatorTransactionResponse response =
                new WithdrawalCalculatorTransactionResponse();
        Optional<WithdrawalCalculatorTransactionResponse> responseOptional = Optional.of(response);

        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Add Transaction Response request: {}"
                , responseOptional);

        if (creditCard.isPresent()) {
            responseOptional = convertResponse(creditCard, request, idUser);
        }
        return responseOptional;
    }

    private Optional<WithdrawalCalculatorTransactionResponse> convert(AddTransactionRequest request, Integer idUser) {
        WithdrawalCalculatorTransactionResponse response =
                new WithdrawalCalculatorTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), idUser);

        return Optional.of(response);
    }

    private Optional<WithdrawalCalculatorTransactionResponse> convertResponse(Optional<CreditCardEntity> creditCard
            , AddTransactionRequest request, Integer idUser) {

        CreditCardEntity creditCardEntity = creditCard.get();

        log.debug("Received Credit Card Entity request: {}", creditCardEntity);

        AddTransactionRequest addTransactionRequest = withdrawalCalculator.withdrawalCalculator(creditCardEntity,
                request);

        addTransactionService.save(addTransactionRequest);
        creditCardRepository.save(creditCardEntity);

        Optional<WithdrawalCalculatorTransactionResponse> responseOptional = convert(addTransactionRequest, idUser);

        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        log.debug("Changed Add Transaction Response request: {}", responseOptional);

        return responseOptional;
    }

}

