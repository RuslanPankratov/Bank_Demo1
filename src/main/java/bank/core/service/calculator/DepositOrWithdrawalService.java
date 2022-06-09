package bank.core.service.calculator;

import bank.core.calculator.DepositOrWithdrawalCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.dto.transaction.DepositOrWithdrawalCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
/*
Better to split into 2 services
DepositService
WithdrawalService
 */
public class DepositOrWithdrawalService {

    private final CreditCardRepository creditCardRepository;
    private final AddTransactionService addTransactionService;

    private final DepositOrWithdrawalCalculator depositOrWithdrawalCalculator;

    public Optional<DepositOrWithdrawalCalculatorTransactionResponse> depositOrWithdrawal(
            AddTransactionRequest request) {

        log.debug("Received Add Transaction request: {}", request);

        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(request.getIdUser());
        DepositOrWithdrawalCalculatorTransactionResponse response =
                new DepositOrWithdrawalCalculatorTransactionResponse();
        Optional<DepositOrWithdrawalCalculatorTransactionResponse> responseOptional = Optional.of(response);

        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Add Transaction Response request: {}"
                ,responseOptional );

        if (creditCard.isPresent()) {

            CreditCardEntity creditCardEntity = creditCard.get();

            log.debug("Received Credit Card Entity request: {}", creditCardEntity);

            AddTransactionRequest addTransactionRequest = depositOrWithdrawalCalculator.calculator(creditCardEntity,
                    request);

            addTransactionService.transaction(addTransactionRequest);

             responseOptional = convert(addTransactionRequest);

            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            log.debug("Changed Add Transaction Response request: {}", responseOptional);

            creditCardRepository.save(creditCardEntity);

        }
        return responseOptional;
    }

    private Optional<DepositOrWithdrawalCalculatorTransactionResponse> convert(AddTransactionRequest request) {
        DepositOrWithdrawalCalculatorTransactionResponse response =
                new DepositOrWithdrawalCalculatorTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getBetweenWhomTheTransaction(), request.getTransactionSuccess(),
                request.getIdUser());

        return Optional.of(response);
    }
}
