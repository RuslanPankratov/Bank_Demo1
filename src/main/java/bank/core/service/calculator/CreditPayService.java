package bank.core.service.calculator;

import bank.core.calculator.LoanPaymentCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.dto.credit.creditPay.CreditPayRequest;
import bank.dto.credit.creditPay.CreditPayTransactionResponse;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.repository.CreditCardRepository;
import bank.repository.CreditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CreditPayService {

    private final CreditCardRepository creditCardRepository;
    private final CreditRepository creditRepository;
    private final AddTransactionService addTransactionService;
    private final LoanPaymentCalculator loanPaymentCalculator;

    public Optional<CreditPayTransactionResponse> pay(CreditPayRequest request) {

        log.debug("Received Credit Pay request: {}", request);

        Optional<CreditEntity> credit = creditRepository.findByIdUser(request.getIdUser());
        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(request.getIdUser());
        CreditPayTransactionResponse creditPayTransactionResponse = new CreditPayTransactionResponse();
        Optional<CreditPayTransactionResponse> creditPayTransactionResponseOptional =
                Optional.of(creditPayTransactionResponse);

        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received Credit Card Entity request: {}", creditCard);

        if (credit.isPresent() && creditCard.isPresent()) {
            CreditEntity creditEntity = credit.get();
            CreditCardEntity creditCardEntity = creditCard.get();

            log.debug("Changed Credit Entity request: {}", creditEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            AddTransactionRequest addTransactionRequest = loanPaymentCalculator.methodPay(creditEntity,
                    creditCardEntity);

            creditPayTransactionResponseOptional = creditPayConvert(addTransactionRequest);

            addTransactionService.transaction(addTransactionRequest);
            creditCardRepository.save(creditCardEntity);
            creditRepository.save(creditEntity);

            log.debug("Changed Add Transaction request: {}", addTransactionRequest);
            log.debug("Changed Credit Entity request: {}", creditEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            log.debug("Return Credit Pay Transaction Response: {}", creditPayTransactionResponseOptional);
        }

        return creditPayTransactionResponseOptional;
    }


    private Optional<CreditPayTransactionResponse> creditPayConvert(AddTransactionRequest request) {
        CreditPayTransactionResponse creditPayTransactionResponse = new CreditPayTransactionResponse(
                request.getAmount(), request.getTransactionType(), request.getBetweenWhomTheTransaction()
                , request.getTransactionSuccess(), request.getIdUser());

        return Optional.of(creditPayTransactionResponse);
    }
}
