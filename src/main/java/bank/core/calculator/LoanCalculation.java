package bank.core.calculator;

import bank.core.strategy.loanCalculation.LoanCalculationStrategy;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.dto.credit.loan.CreditLoanRequest;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.enum_class.WithWhomTheDeal;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.enum_class.TypeOfBenefits;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class LoanCalculation {

    private List<LoanCalculationStrategy> loanCalculationStrategies;

    public AddTransactionRequest interestRateMethod(UserEntity user, CreditEntity credit,
                                                    CreditCardEntity creditCardEntity,
                                                    CreditLoanRequest creditLoanRequest) {

        log.debug("Received User Entity request: {}", user);
        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received Credit Card Entity request: {}", creditCardEntity);
        log.debug("Received Credit Loan Request request: {}", creditLoanRequest);

        for (int i = 0; i < loanCalculationStrategies.size(); i++) {
            loanCalculationStrategies.get(i).action(user, credit, creditCardEntity, creditLoanRequest);
        }
        return requestConvert(creditLoanRequest, creditCardEntity);
    }

    private AddTransactionRequest requestConvert(CreditLoanRequest creditLoanRequest
            , CreditCardEntity creditCardEntity) {
        return new AddTransactionRequest(creditLoanRequest.getAmountOfCredit(),
                TransactionType.RECEIVING, WithWhomTheDeal.CREDIT, TransactionSuccess.SUCCESSFUL,
                creditCardEntity.getIdUser());
    }
}
