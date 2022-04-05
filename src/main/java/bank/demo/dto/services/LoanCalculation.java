package bank.demo.dto.services;

import bank.demo.dto.scanner.ScannerLoanCalculation;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.Credit;

public class LoanCalculation {

    private BankAccount bankAccount;
    private double currentPercentUser;

    public LoanCalculation(BankAccount bankAccount, double currentPercentUser) {
        this.bankAccount = bankAccount;
        this.currentPercentUser = currentPercentUser;
    }

    public void interestRateMethod() {
        double discount = 0;

        if (bankAccount.getCredit() == null) {
            bankAccount.setCredit(new Credit());
        }
        if (bankAccount.getUser().getTypeOfBenefits().equals(TypeOfBenefits.NO_BENEFITS)) {

            customerCosting(bankAccount, currentPercentUser);

        } else if (bankAccount.getUser().getTypeOfBenefits().equals(TypeOfBenefits.DISABILITY_ONE_TWO)) {
            //57
            discount = 57;
            double count = currentPercentUser / 100 * discount;
            currentPercentUser = currentPercentUser - count;
            customerCosting(bankAccount, currentPercentUser);

        } else if (bankAccount.getUser().getTypeOfBenefits().equals(TypeOfBenefits.DISABILITY_THREE_FOUR)) {
            discount = 21;
            double count = currentPercentUser / 100 * discount;
            currentPercentUser = currentPercentUser - count;
            customerCosting(bankAccount, currentPercentUser);

        } else if (bankAccount.getUser().getTypeOfBenefits().equals(TypeOfBenefits.THE_LARGE_FAMILY)) {
            discount = 28;
            double count = currentPercentUser / 100 * discount;
            currentPercentUser = currentPercentUser - count;
            customerCosting(bankAccount, currentPercentUser);

        } else if (bankAccount.getUser().getTypeOfBenefits().equals(TypeOfBenefits.PENSIONER)) {
            discount = 10;
            double count = currentPercentUser / 100 * discount;
            currentPercentUser = currentPercentUser - count;
            customerCosting(bankAccount, currentPercentUser);

        } else if (bankAccount.getUser().getTypeOfBenefits().equals(TypeOfBenefits.VETERAN)) {
            discount = 23;
            double count = currentPercentUser / 100 * discount;
            currentPercentUser = currentPercentUser - count;
            customerCosting(bankAccount, currentPercentUser);

        }
    }

    void customerCosting(BankAccount bankAccount, double currentPercentUser) {
        double countMonth = bankAccount.getCredit().getCountMonthsToPay();
        double year = countMonth / 12;
        double onePercent = bankAccount.getCredit().getHowMuchIsTheLoan() / 100;
        double percentOverpaid = onePercent * currentPercentUser * year;
        double paymentAmountPercent = bankAccount.getCredit().getHowMuchIsTheLoan() + percentOverpaid;
        double resultPaymentMonth = paymentAmountPercent / countMonth;

        System.out.println("month pay " + resultPaymentMonth);
        System.out.println("how much do you have to pay " + percentOverpaid);
        System.out.println("total payment amount " + paymentAmountPercent);

        System.out.println("Are you sure you want to take out a loan? Yes or No");


        ScannerLoanCalculation scannerLoanCalculation = new ScannerLoanCalculation();
        scannerLoanCalculation.loanCalc(bankAccount, paymentAmountPercent, currentPercentUser, countMonth, percentOverpaid,
                resultPaymentMonth);
    }
}
