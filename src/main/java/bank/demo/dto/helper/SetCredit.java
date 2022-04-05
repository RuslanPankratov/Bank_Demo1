package bank.demo.dto.helper;

import bank.demo.dto.dto.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class SetCredit {

    public void creditSet(BankAccount bankAccount, double paymentAmountPercent, double currentPercentUser, double countMonth,
                          double percentOverpaid, double resultPaymentMonth) {
        bankAccount.getCredit().setHowMuchToPay(paymentAmountPercent);
        bankAccount.getCredit().setPercentRate(currentPercentUser);
        bankAccount.getCredit().setPaid(0);
        bankAccount.getCredit().setTheTotalAmountYouPay(paymentAmountPercent);
        bankAccount.getCredit().setCountMonthsToPay(countMonth);
        bankAccount.getCredit().setBankProfit(percentOverpaid);
        bankAccount.getCredit().setPaymentPerMonth(resultPaymentMonth);
    }
}
