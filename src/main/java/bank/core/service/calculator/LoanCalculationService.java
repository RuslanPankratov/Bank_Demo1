package bank.core.service.calculator;


import bank.core.calculator.LoanCalculation;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.dto.credit.loan.CreditLoanCalculationTransactionResponse;
import bank.dto.credit.loan.CreditLoanRequest;
import bank.dto.transaction.add.AddTransactionRequest;
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

    public CreditLoanCalculationTransactionResponse loan(CreditLoanRequest request, Integer idUser, Integer idCredit
            , Integer idCreditCard) {

        log.debug("Received Credit Loan request: {}", request);

        Optional<CreditEntity> credit = creditRepository.findById(idCredit);
        Optional<UserEntity> user = userRepository.findById(idUser);
        Optional<CreditCardEntity> creditCard = creditCardRepository.findById(idCreditCard);
        CreditLoanCalculationTransactionResponse response = new CreditLoanCalculationTransactionResponse();

        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received User Entity request: {}", user);
        log.debug("Received Credit Card Entity request: {}", creditCard);
        log.debug("Received Credit Loan Calculation Transaction Response: {}", response);

        if (credit.isPresent() && user.isPresent() && creditCard.isPresent()) {
            response = convertResponse(creditCard.get(), credit.get(), user.get(), request);
        }

        return response;
    }

    private CreditLoanCalculationTransactionResponse convert(AddTransactionRequest request) {
        return new CreditLoanCalculationTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getWithWhomTheDeal()
                , request.getTransactionSuccess(), request.getIdUser());
    }


    private CreditLoanCalculationTransactionResponse convertResponse(CreditCardEntity creditCard
            , CreditEntity credit, UserEntity user, CreditLoanRequest request) {

        AddTransactionRequest addTransactionRequest = loanCalculation.interestRateMethod(user, credit
                , creditCard, request);

        addTransactionService.save(addTransactionRequest);
        creditRepository.save(credit);
        creditCardRepository.save(creditCard);

        CreditLoanCalculationTransactionResponse responseOptional = convert(addTransactionRequest);

        log.debug("Changed Credit Entity request: {}", credit);
        log.debug("Changed User Entity request: {}", user);
        log.debug("Changed Credit Card Entity request: {}", creditCard);
        log.debug("Changed Credit Loan Calculation Response request: {}", responseOptional);

        return responseOptional;
    }
}
