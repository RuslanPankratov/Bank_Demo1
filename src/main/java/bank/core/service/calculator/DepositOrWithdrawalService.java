package bank.core.service.calculator;


import bank.core.calculator.DepositOrWithdrawalCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.dto.transaction.AddTransactionRequest;
import bank.dto.transaction.AddTransactionResponse;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DepositOrWithdrawalService {

    private final CreditCardRepository creditCardRepository;
    private final AddTransactionService addTransactionService;

    public Optional<AddTransactionResponse> depositOrWithdrawal(AddTransactionRequest request) {

        log.debug("Received Add Transaction request: {}", request);

        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(request.getIdUser());
        AddTransactionResponse addTransactionResponse = new AddTransactionResponse();
        Optional<AddTransactionResponse> addTransactionResponseOptional = Optional.of(addTransactionResponse);

        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Add Transaction Response request: {}", addTransactionResponseOptional);

        if (creditCard.isPresent()) {
            DepositOrWithdrawalCalculator depositOrWithdrawalCalculator = new DepositOrWithdrawalCalculator();
            CreditCardEntity creditCardEntity = creditCard.get();

            log.debug("Received Credit Card Entity request: {}", creditCardEntity);

            AddTransactionRequest addTransactionRequest = depositOrWithdrawalCalculator.calculator(creditCardEntity,
                    request);

            addTransactionService.transaction(addTransactionRequest);

            addTransactionResponseOptional = convert(addTransactionRequest);

            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            log.debug("Changed Add Transaction Response request: {}", addTransactionResponseOptional);

        }
        return addTransactionResponseOptional;
    }

    private Optional<AddTransactionResponse> convert(AddTransactionRequest request) {
        return Optional.of(new AddTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getBetweenWhomTheTransaction(), request.getTransactionSuccess(),
                request.getIdUser()));
    }
}
