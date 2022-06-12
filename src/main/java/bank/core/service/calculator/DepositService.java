package bank.core.service.calculator;

import bank.core.calculator.DepositCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.core.service.credit.dto.transaction.DepositCalculatorTransactionResponse;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class DepositService {

    private final CreditCardRepository creditCardRepository;
    private final AddTransactionService addTransactionService;
    private final DepositCalculator depositCalculator;

    public Optional<DepositCalculatorTransactionResponse> deposit(
            AddTransactionRequest request, Integer idUser) {

        log.debug("Received Add Transaction request: {}", request);

        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(idUser);
        DepositCalculatorTransactionResponse response =
                new DepositCalculatorTransactionResponse();
        Optional<DepositCalculatorTransactionResponse> responseOptional = Optional.of(response);

        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Add Transaction Response request: {}"
                , responseOptional);

        if (creditCard.isPresent()) {
            responseOptional = responseOptionalConvert(creditCard, request, idUser);
        }
        return responseOptional;
    }

    private Optional<DepositCalculatorTransactionResponse> convert(AddTransactionRequest request, Integer idUser) {
        DepositCalculatorTransactionResponse response =
                new DepositCalculatorTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), idUser);

        return Optional.of(response);
    }


    private Optional<DepositCalculatorTransactionResponse> responseOptionalConvert(Optional<CreditCardEntity> creditCard
            , AddTransactionRequest request, Integer idUser) {

        CreditCardEntity creditCardEntity = creditCard.get();

        AddTransactionRequest addTransactionRequest = depositCalculator.depositCalculator(creditCardEntity,
                request);

        addTransactionService.save(addTransactionRequest);

        Optional<DepositCalculatorTransactionResponse> responseOptional = convert(addTransactionRequest, idUser);

        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        log.debug("Changed Add Transaction Response request: {}", responseOptional);

        creditCardRepository.save(creditCardEntity);

        return responseOptional;
    }
}
