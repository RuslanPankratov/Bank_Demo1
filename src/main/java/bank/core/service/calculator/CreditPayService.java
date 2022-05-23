package bank.core.service.calculator;

import bank.core.calculator.LoanPaymentCalculator;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.dto.credit.creditPay.CreditPayRequest;
import bank.dto.credit.creditPay.CreditPayResponse;
import bank.dto.transaction.AddTransactionRequest;
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

    public Optional<CreditPayResponse> pay(CreditPayRequest request) {

        log.debug("Received Credit Pay request: {}", request);

        Optional<CreditEntity> credit = creditRepository.findByIdUser(request.getIdUser());
        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(request.getIdUser());
        CreditPayResponse creditPayResponse = new CreditPayResponse();
        Optional<CreditPayResponse> creditPayResponseOptional = Optional.of(creditPayResponse);

        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Credit Pay Response request: {}", creditPayResponseOptional);


        if (credit.isPresent() && creditCard.isPresent()) {
            CreditEntity creditEntity = credit.get();
            CreditCardEntity creditCardEntity = creditCard.get();
            LoanPaymentCalculator loanPaymentCalculator = new LoanPaymentCalculator();

            log.debug("Changed Credit Entity request: {}", creditEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            AddTransactionRequest addTransactionRequest = loanPaymentCalculator.methodPay(creditEntity,
                    creditCardEntity);

            addTransactionService.transaction(addTransactionRequest);
            creditCardRepository.save(creditCardEntity);
            creditRepository.save(creditEntity);

            creditPayResponseOptional = convert(creditEntity);

            log.debug("Changed Add Transaction request: {}", addTransactionRequest);
            log.debug("Changed Credit Entity request: {}", creditEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            log.debug("Changed Credit Pay Response request: {}", creditPayResponseOptional);
        }

        return creditPayResponseOptional;
    }


    private Optional<CreditPayResponse> convert(CreditEntity entity) {
        return Optional.of(new CreditPayResponse(entity.getIdCredit(), entity.getHowMuchToPay(),
                entity.getPercentRate(), entity.getPaid(), entity.getTheTotalAmountYouPay(),
                entity.getCountMonthsToPay(),
                entity.getBankProfit(), entity.getHowMuchIsTheLoan(), entity.getPaymentPerMonth(), entity.getIdUser()));
    }
}
