package bank.core.strategy.loanCalculation;

import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.dto.credit.loan.CreditLoanRequest;

public interface LoanCalculationStrategy {

    void action(UserEntity user, CreditEntity credit, CreditCardEntity creditCardEntity
            , CreditLoanRequest creditLoanRequest);

}
