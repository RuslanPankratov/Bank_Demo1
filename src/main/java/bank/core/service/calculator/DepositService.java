package bank.core.service.calculator;

import bank.core.calculator.DepositCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.dto.transaction.DepositCalculatorTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
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

    public DepositCalculatorTransactionResponse deposit(
            AddTransactionRequest request, Integer idCreditCard) {

        log.debug("Received Add Transaction request: {}", request);

        Optional<CreditCardEntity> creditCard = creditCardRepository.findById(idCreditCard);
        DepositCalculatorTransactionResponse response =
                new DepositCalculatorTransactionResponse();


        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Add Transaction Response request: {}" , response);

        if (creditCard.isPresent()) {
            response = responseOptionalConvert(creditCard.get(), request, idCreditCard);
        }
        return response;
    }

    private DepositCalculatorTransactionResponse convert(AddTransactionRequest request, Integer idUser) {
        return new DepositCalculatorTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), idUser);
    }


    private DepositCalculatorTransactionResponse responseOptionalConvert(CreditCardEntity creditCard
            , AddTransactionRequest request, Integer idCreditCard) {

        AddTransactionRequest addTransactionRequest = depositCalculator.depositCalculator(creditCard,
                request);

        addTransactionService.save(addTransactionRequest);

        DepositCalculatorTransactionResponse responseOptional = convert(addTransactionRequest, idCreditCard);

        log.debug("Changed Credit Card Entity request: {}", creditCard);
        log.debug("Changed Add Transaction Response request: {}", responseOptional);

        creditCardRepository.save(creditCard);

        return responseOptional;
    }
}
