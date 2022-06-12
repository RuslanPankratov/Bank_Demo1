package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.core.service.credit.dto.credit.loan.CreditLoanRequest;
import bank.enum_class.TypeOfBenefits;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LoanCalculationTest {

    LoanCalculation loanCalculation = new LoanCalculation();

    @Test
    void interestRateMethodNoBenefitsTest() {
        UserEntity user = addUser();
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();
        CreditLoanRequest creditLoanRequest = addCreditLoanRequest();

        loanCalculation.interestRateMethod(user, credit, creditCardEntity, creditLoanRequest);

        BigDecimal result = credit.getHowMuchToPay().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(30000).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void interestRateMethodDisabilityOneTwoTest() {
        UserEntity user = addUser();
        user.setTypeOfBenefits(TypeOfBenefits.DISABILITY_ONE_TWO);
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();
        CreditLoanRequest creditLoanRequest = addCreditLoanRequest();

        loanCalculation.interestRateMethod(user, credit, creditCardEntity, creditLoanRequest);

        BigDecimal result = credit.getHowMuchToPay().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(24300).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void interestRateMethodDisabilityThreeFourTest() {
        UserEntity user = addUser();
        user.setTypeOfBenefits(TypeOfBenefits.DISABILITY_THREE_FOUR);
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();
        CreditLoanRequest creditLoanRequest = addCreditLoanRequest();

        loanCalculation.interestRateMethod(user, credit, creditCardEntity, creditLoanRequest);

        BigDecimal result = credit.getHowMuchToPay().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(27900).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void interestRateMethodTheLargeFamily() {
        UserEntity user = addUser();
        user.setTypeOfBenefits(TypeOfBenefits.THE_LARGE_FAMILY);
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();
        CreditLoanRequest creditLoanRequest = addCreditLoanRequest();

        loanCalculation.interestRateMethod(user, credit, creditCardEntity, creditLoanRequest);

        BigDecimal result = credit.getHowMuchToPay().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(27200).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void interestRateMethodPensioner() {
        UserEntity user = addUser();
        user.setTypeOfBenefits(TypeOfBenefits.PENSIONER);
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();
        CreditLoanRequest creditLoanRequest = addCreditLoanRequest();

        loanCalculation.interestRateMethod(user, credit, creditCardEntity, creditLoanRequest);

        BigDecimal result = credit.getHowMuchToPay().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(29000).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }

    @Test
    void interestRateMethodVeteran() {
        UserEntity user = addUser();
        user.setTypeOfBenefits(TypeOfBenefits.VETERAN);
        CreditEntity credit = addCreditEntity();
        CreditCardEntity creditCardEntity = addCreditCard();
        CreditLoanRequest creditLoanRequest = addCreditLoanRequest();

        loanCalculation.interestRateMethod(user, credit, creditCardEntity, creditLoanRequest);

        BigDecimal result = credit.getHowMuchToPay().setScale(2, RoundingMode.CEILING);
        BigDecimal expectedResult = new BigDecimal(27700).setScale(2, RoundingMode.CEILING);

        assertEquals(expectedResult, result);
    }


    private UserEntity addUser() {
        return new UserEntity(2, "Ruslan", "Pankratov", 20,
                TypeOfBenefits.NO_BENEFITS);
    }

    private CreditEntity addCreditEntity() {
        return new CreditEntity(2, new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), new BigDecimal(0), new BigDecimal(0), new BigDecimal(0)
                , new BigDecimal(0), 2);
    }

    private CreditCardEntity addCreditCard() {
        return new CreditCardEntity(2, "login", "pas"
                , new BigDecimal(2000), new BigDecimal(1000), 2);
    }

    private CreditLoanRequest addCreditLoanRequest() {
        CreditLoanRequest creditLoanRequest = new CreditLoanRequest();
       // creditLoanRequest.setIdUser(2);
        creditLoanRequest.setCurrentPercentUser(new BigDecimal(2));
        creditLoanRequest.setNumberOfMonthsOfLoan(new BigDecimal(300));
        creditLoanRequest.setAmountOfCredit(new BigDecimal(20000));
        return creditLoanRequest;
    }

}