package bank.core.service.calculator;

import bank.core.calculator.LoanPaymentCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
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

    public CreditPayTransactionResponse pay(Integer idCredit, Integer idCreditCard) {

        log.debug("Received Id Credit: {}", idCredit);
        log.debug("Received Credit Card: {}", idCreditCard);

        Optional<CreditEntity> credit = creditRepository.findById(idCredit);
        Optional<CreditCardEntity> creditCard = creditCardRepository.findById(idCreditCard);
        CreditPayTransactionResponse creditPayTransactionResponse = new CreditPayTransactionResponse();

        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received Credit Card Entity request: {}", creditCard);

        if (credit.isPresent() && creditCard.isPresent()) {
            AddTransactionRequest addTransactionRequest = new AddTransactionRequest();
            creditPayTransactionResponse = response(credit.get(), creditCard.get());
            save(credit.get(), creditCard.get(), creditPayTransactionResponse, addTransactionRequest);
        }
        return creditPayTransactionResponse;
    }


    private CreditPayTransactionResponse creditPayConvert(AddTransactionRequest request) {
        return new CreditPayTransactionResponse(request.getAmount(), request.getTransactionType()
                , request.getWithWhomTheDeal(), request.getTransactionSuccess(), request.getIdUser());
    }

    private void save(CreditEntity credit, CreditCardEntity creditCard
            , CreditPayTransactionResponse creditPayTransactionResponseOptional
            , AddTransactionRequest addTransactionRequest) {

        addTransactionService.save(addTransactionRequest);
        creditCardRepository.save(creditCard);
        creditRepository.save(credit);

        log.debug("Changed Add Transaction request: {}", addTransactionRequest);
        log.debug("Changed Credit Entity request: {}", credit);
        log.debug("Changed Credit Card Entity request: {}", creditCard);
        log.debug("Return Credit Pay Transaction Response: {}", creditPayTransactionResponseOptional);
    }

    private CreditPayTransactionResponse response(CreditEntity credit
            , CreditCardEntity creditCard) {
        log.debug("Changed Credit Entity request: {}", credit);
        log.debug("Changed Credit Card Entity request: {}", creditCard);

        AddTransactionRequest addTransactionRequest = loanPaymentCalculator.methodPay(credit, creditCard);

        return creditPayConvert(addTransactionRequest);
    }
}
