package bank.core.strategy.loanCalculation;

import bank.domain.CreditCardEntity;
import bank.domain.CreditEntity;
import bank.dto.credit.loan.CreditLoanRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Slf4j
public class HelperLoanCalculationStrategy {


    public void calculatedInterest(BigDecimal discount, CreditEntity credit,
                                   CreditCardEntity creditCardEntity,
                                   CreditLoanRequest creditLoanRequest) {
        BigDecimal count = creditLoanRequest.getCurrentPercentUser().divide(new BigDecimal(100), 2,
                RoundingMode.HALF_UP).multiply(discount);
        creditLoanRequest.setCurrentPercentUser(creditLoanRequest.getCurrentPercentUser().subtract(count));

        customerCosting(credit, creditCardEntity, creditLoanRequest);
    }

    public void customerCosting(CreditEntity credit, CreditCardEntity creditCardEntity,
                                CreditLoanRequest creditLoanRequest) {

        convert(credit, creditCardEntity, creditLoanRequest);
        log.debug("Changed Credit Entity request: {}", credit);
        log.debug("Changed Credit Card Entity request: {}", creditCardEntity);
    }

    private void convert(CreditEntity credit, CreditCardEntity creditCardEntity,
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

        creditCardEntity.setInvoiceAmount(creditLoanRequest.getAmountOfCredit());
    }
}
