package bank.core.calculator;

import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.domain.UserEntity;
import bank.dto.credit.loan.CreditLoanRequest;
import bank.dto.transaction.AddTransactionRequest;
import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import bank.enum_class.TypeOfBenefits;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
public class LoanCalculation {

    public AddTransactionRequest interestRateMethod(UserEntity user, CreditEntity credit,
                                                    CreditCardEntity creditCardEntity,
                                                    CreditLoanRequest creditLoanRequest) {

        log.debug("Received User Entity request: {}", user);
        log.debug("Received Credit Entity request: {}", credit);
        log.debug("Received Credit Card Entity request: {}", creditCardEntity);
        log.debug("Received Credit Loan Request request: {}", creditLoanRequest);

        BigDecimal discount = new BigDecimal(0);

        if (user.getTypeOfBenefits().equals(TypeOfBenefits.NO_BENEFITS)) {
            return customerCosting(credit, creditCardEntity, creditLoanRequest);

        } else if (user.getTypeOfBenefits().equals(TypeOfBenefits.DISABILITY_ONE_TWO)) {
            discount = new BigDecimal(57);
            return calculatedInterest(discount, credit, creditCardEntity, creditLoanRequest);

        } else if (user.getTypeOfBenefits().equals(TypeOfBenefits.DISABILITY_THREE_FOUR)) {
            discount = new BigDecimal(21);
            return calculatedInterest(discount, credit, creditCardEntity, creditLoanRequest);

        } else if (user.getTypeOfBenefits().equals(TypeOfBenefits.THE_LARGE_FAMILY)) {
            discount = new BigDecimal(28);
            return calculatedInterest(discount, credit, creditCardEntity, creditLoanRequest);

        } else if (user.getTypeOfBenefits().equals(TypeOfBenefits.PENSIONER)) {
            discount = new BigDecimal(10);
            return calculatedInterest(discount, credit, creditCardEntity, creditLoanRequest);

        } else if (user.getTypeOfBenefits().equals(TypeOfBenefits.VETERAN)) {
            discount = new BigDecimal(23);
            return calculatedInterest(discount, credit, creditCardEntity, creditLoanRequest);
        }
        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(creditLoanRequest.getAmountOfCredit(),
                TransactionType.RECEIVING, BetweenWhomTheTransaction.CREDIT, TransactionSuccess.NO_TYPE_OF_BENEFITS,
                creditCardEntity.getIdUser());

        log.debug("Return Add Transaction Request: {}", addTransactionRequest);

        return addTransactionRequest;
    }

    private AddTransactionRequest calculatedInterest(BigDecimal discount, CreditEntity credit,
                                                     CreditCardEntity creditCardEntity,
                                                     CreditLoanRequest creditLoanRequest) {
        BigDecimal count = creditLoanRequest.getCurrentPercentUser().divide(new BigDecimal(100), 2,
                RoundingMode.HALF_UP).multiply(discount);
        creditLoanRequest.setCurrentPercentUser(creditLoanRequest.getCurrentPercentUser().subtract(count));

        return customerCosting(credit, creditCardEntity, creditLoanRequest);
    }

    private AddTransactionRequest customerCosting(CreditEntity credit, CreditCardEntity creditCardEntity,
                                                  CreditLoanRequest creditLoanRequest) {

        BigDecimal countMonth = creditLoanRequest.getNumberOfMonthsOfLoan();
        BigDecimal year = countMonth.divide(new BigDecimal(12), 2, RoundingMode.HALF_UP);
        BigDecimal onePercent = creditLoanRequest.getAmountOfCredit().divide(new BigDecimal(100), 2,
                RoundingMode.HALF_UP);
        BigDecimal percentOverpaid = onePercent.multiply(creditLoanRequest.getCurrentPercentUser()).multiply(year);
        BigDecimal paymentAmountPercent = creditLoanRequest.getAmountOfCredit().add(percentOverpaid);
        BigDecimal paymentPerMonth = paymentAmountPercent.divide(countMonth, 2, RoundingMode.HALF_UP);


        credit.setCountMonthsToPay(credit.getCountMonthsToPay().add(creditLoanRequest.getNumberOfMonthsOfLoan()));
        credit.setHowMuchIsTheLoan(credit.getHowMuchIsTheLoan().add(creditLoanRequest.getAmountOfCredit()));
        credit.setBankProfit(credit.getBankProfit().add(percentOverpaid));
        credit.setPaymentPerMonth(credit.getPaymentPerMonth().add(paymentPerMonth));
        credit.setHowMuchToPay(credit.getHowMuchToPay().add(paymentAmountPercent));
        credit.setTheTotalAmountYouPay(credit.getTheTotalAmountYouPay().add(paymentAmountPercent));
        credit.setPercentRate(credit.getPercentRate().add(creditLoanRequest.getCurrentPercentUser()));
        if (credit.getPaid() != null) {
            credit.setPaid(new BigDecimal(0));
        }
        creditCardEntity.setInvoiceAmount(creditLoanRequest.getAmountOfCredit());

        log.debug("Changed Credit Entity request: {}", credit);
        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);

        AddTransactionRequest addTransactionRequest = new AddTransactionRequest(creditLoanRequest.getAmountOfCredit(),
                TransactionType.RECEIVING, BetweenWhomTheTransaction.CREDIT, TransactionSuccess.SUCCESSFUL,
                creditCardEntity.getIdUser());

        log.debug("Return Add Transaction Request: {}", addTransactionRequest);

        return addTransactionRequest;
    }
}
