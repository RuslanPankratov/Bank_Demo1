package bank.core.strategy.loanCalculation.impl;

import bank.core.strategy.loanCalculation.HelperLoanCalculationStrategy;
import bank.core.strategy.loanCalculation.LoanCalculationStrategy;
import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.dto.credit.loan.CreditLoanRequest;
import bank.enum_class.TypeOfBenefits;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class DisabilityOneTwoStrategy implements LoanCalculationStrategy {

    private HelperLoanCalculationStrategy helperLoanCalculationStrategy;

    @Override
    public void action(UserEntity user, CreditEntity credit, CreditCardEntity creditCardEntity
            , CreditLoanRequest creditLoanRequest) {
        if (user.getTypeOfBenefits().equals(TypeOfBenefits.DISABILITY_ONE_TWO)) {
            BigDecimal discount = new BigDecimal(57);
            helperLoanCalculationStrategy.calculatedInterest(discount, credit, creditCardEntity, creditLoanRequest);
        }
    }
}
