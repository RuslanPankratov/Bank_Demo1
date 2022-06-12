package bank.core.service.calculator;


import bank.core.calculator.LoanCalculation;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.core.service.credit.dto.credit.loan.CreditLoanCalculationTransactionResponse;
import bank.core.service.credit.dto.credit.loan.CreditLoanRequest;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
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
    private LoanCalculation loanCalculation;

    public Optional<CreditLoanCalculationTransactionResponse> loan(CreditLoanRequest request, Integer idUser) {

        log.debug("Received Credit Loan request: {}", request);

        Optional<CreditEntity> credit = creditRepository.findByIdUser(idUser);
        Optional<UserEntity> user = userRepository.findById(idUser);
        Optional<CreditCardEntity> creditCard = creditCardRepository.findByIdUser(idUser);
        CreditLoanCalculationTransactionResponse response = new CreditLoanCalculationTransactionResponse();
        Optional<CreditLoanCalculationTransactionResponse> responseOptional = Optional.of(response);

        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received User Entity request: {}", user);
        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Credit Loan Calculation Transaction Response: {}", responseOptional);

        if (credit.isPresent() && user.isPresent() && creditCard.isPresent()) {
            responseOptional = convertResponse(creditCard, credit, user, request);
        }

        return responseOptional;
    }

    private Optional<CreditLoanCalculationTransactionResponse> convert(AddTransactionRequest request) {
        CreditLoanCalculationTransactionResponse response =
                new CreditLoanCalculationTransactionResponse(request.getAmount(),
                        request.getTransactionType(), request.getWithWhomTheDeal()
                        , request.getTransactionSuccess(), request.getIdUser());

        return Optional.of(response);
    }


    private Optional<CreditLoanCalculationTransactionResponse> convertResponse(Optional<CreditCardEntity> creditCard
            , Optional<CreditEntity> credit, Optional<UserEntity> user, CreditLoanRequest request) {

        CreditEntity creditEntity = credit.get();
        UserEntity userEntity = user.get();
        CreditCardEntity creditCardEntity = creditCard.get();

        AddTransactionRequest addTransactionRequest = loanCalculation.interestRateMethod(userEntity, creditEntity
                , creditCardEntity, request);

        addTransactionService.save(addTransactionRequest);
        creditRepository.save(creditEntity);
        creditCardRepository.save(creditCardEntity);

        Optional<CreditLoanCalculationTransactionResponse> responseOptional = convert(addTransactionRequest);

        log.debug("Changed Credit Entity request: {}", creditEntity);
        log.debug("Changed User Entity request: {}", userEntity);
        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
        log.debug("Changed Credit Loan Calculation Response request: {}", responseOptional);

        return responseOptional;
    }
}
