package bank.core.service.calculator;


import bank.core.calculator.LoanCalculation;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.dto.credit.loan.CreditLoanCalculationResponse;
import bank.dto.credit.loan.CreditLoanRequest;
import bank.dto.transaction.AddTransactionRequest;
import bank.repository.CreditCardRepository;
import bank.repository.CreditRepository;
import bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class LoanCalculationService {

    private final UserRepository userRepository;
    private final CreditRepository creditRepository;
    private final CreditCardRepository creditCardRepository;
    private final AddTransactionService addTransactionService;

    public Optional<CreditLoanCalculationResponse> loan(CreditLoanRequest request) {

        log.debug("Received Credit Loan request: {}", request);

        Optional<CreditEntity> credit = creditRepository.findByIdUser(request.getIdUser());
        Optional<UserEntity> user = userRepository.findById(request.getIdUser());
        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(request.getIdUser());
        CreditLoanCalculationResponse creditLoanCalculationResponse = new CreditLoanCalculationResponse();
        Optional<CreditLoanCalculationResponse> creditLoanCalculationResponseOptional =
                Optional.of(creditLoanCalculationResponse);

        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received User Entity request: {}", user);
        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Credit Loan Calculation Response request: {}", creditLoanCalculationResponseOptional);

        if (credit.isPresent() && user.isPresent() && creditCard.isPresent()) {
            CreditEntity creditEntity = credit.get();
            UserEntity userEntity = user.get();
            CreditCardEntity creditCardEntity = creditCard.get();

            log.debug("Received Credit Entity request: {}", creditEntity);
            log.debug("Received User Entity request: {}", userEntity);
            log.debug("Received Credit Card Entity request: {}", creditCardEntity);

            LoanCalculation loanCalculation = new LoanCalculation();
            AddTransactionRequest addTransactionRequest = loanCalculation.interestRateMethod(userEntity, creditEntity
                    , creditCardEntity, request);

            addTransactionService.transaction(addTransactionRequest);
            creditRepository.save(creditEntity);
            creditCardRepository.save(creditCardEntity);

            creditLoanCalculationResponseOptional = convert(creditEntity);

            log.debug("Changed Credit Entity request: {}", creditEntity);
            log.debug("Changed User Entity request: {}", userEntity);
            log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
            log.debug("Changed Credit Loan Calculation Response request: {}", creditLoanCalculationResponseOptional);

        }
        return creditLoanCalculationResponseOptional;
    }

    private Optional<CreditLoanCalculationResponse> convert(CreditEntity entity) {
        return Optional.of(new CreditLoanCalculationResponse(entity.getIdCredit(), entity.getHowMuchToPay(),
                entity.getPercentRate(), entity.getPaid(), entity.getTheTotalAmountYouPay(),
                entity.getCountMonthsToPay(), entity.getBankProfit(), entity.getHowMuchIsTheLoan(),
                entity.getPaymentPerMonth(), entity.getIdUser()));
    }
}
