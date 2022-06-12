package bank.core.service.calculator;

import bank.core.calculator.LoanPaymentCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.core.service.credit.dto.credit.creditPay.CreditPayTransactionResponse;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
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

    public Optional<CreditPayTransactionResponse> pay(Integer idUser) {

        log.debug("Received Credit Pay request: {}", idUser);

        Optional<CreditEntity> credit = creditRepository.findByIdUser(idUser);
        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(idUser);
        CreditPayTransactionResponse creditPayTransactionResponse = new CreditPayTransactionResponse();
        Optional<CreditPayTransactionResponse> creditPayTransactionResponseOptional =
                Optional.of(creditPayTransactionResponse);

        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received Credit Card Entity request: {}", creditCard);

        if (credit.isPresent() && creditCard.isPresent()) {
            AddTransactionRequest addTransactionRequest = new AddTransactionRequest();
            creditPayTransactionResponseOptional = response(credit,creditCard,addTransactionRequest);
            save(credit,creditCard,creditPayTransactionResponseOptional,addTransactionRequest);
        }
        return creditPayTransactionResponseOptional;
    }


    private Optional<CreditPayTransactionResponse> creditPayConvert(AddTransactionRequest request) {
        CreditPayTransactionResponse creditPayTransactionResponse = new CreditPayTransactionResponse(
                request.getAmount(), request.getTransactionType(), request.getWithWhomTheDeal()
                , request.getTransactionSuccess(), request.getIdUser());

        return Optional.of(creditPayTransactionResponse);
    }

    private void save(Optional<CreditEntity> credit, Optional<CreditCardEntity> creditCard
            , Optional<CreditPayTransactionResponse> creditPayTransactionResponseOptional
            ,AddTransactionRequest addTransactionRequest) {

        addTransactionService.save(addTransactionRequest);
        creditCardRepository.save(creditCard.get());
        creditRepository.save(credit.get());

        log.debug("Changed Add Transaction request: {}", addTransactionRequest);
        log.debug("Changed Credit Entity request: {}", credit);
        log.debug("Changed Credit Card Entity request: {}", creditCard);
        log.debug("Return Credit Pay Transaction Response: {}", creditPayTransactionResponseOptional);
    }

    private  Optional<CreditPayTransactionResponse> response(Optional<CreditEntity> credit
            , Optional<CreditCardEntity> creditCard, AddTransactionRequest addTransactionRequest){
        CreditEntity creditEntity = credit.get();
        CreditCardEntity creditCardEntity = creditCard.get();

        log.debug("Changed Credit Entity request: {}", creditEntity);
        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        addTransactionRequest = loanPaymentCalculator.methodPay(credit.get(),
                creditCard.get());

       return creditPayConvert(addTransactionRequest);
    }
}
