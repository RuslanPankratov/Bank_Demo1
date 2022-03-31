package bank.demo.dto.services.cardEnter;

import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.Credit;
import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.scanner.ScannerCardEntry;
import bank.demo.dto.services.LoanCalculation;
import bank.demo.dto.services.LoanPaymentCalculator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Component
public class CreditCardEntryIMPL implements CardEntryIMPL{


    @Override
    public void menu(ListBankAccount listBankAccount, int i) {
        methodCredit(listBankAccount.getBankAccountList().get(i));
    }

    @Override
    public String getActionName() {
        return "Credit";
    }

    void methodCredit(BankAccount bankAccount) {
        String result = new ScannerCardEntry().scannerMethodCredit();


        if (result.equals("1")) {
            if (bankAccount.getCredit() == null) {
                Credit credit = new Credit();
                bankAccount.setCredit(credit);
            }
            if (bankAccount.getCredit().getCountMonthsToPay() <= 0) {
                double credit = new ScannerCardEntry().scannerCredit();

                bankAccount.getCredit().setHowMuchIsTheLoan(credit);
                double month = new ScannerCardEntry().scannerMonth();
                bankAccount.getCredit().setCountMonthsToPay(month);

                LoanCalculation loanCalculation = new LoanCalculation(bankAccount, 5);
                loanCalculation.interestRateMethod();
            }


        } else if (result.equals("2")) {
            String resultTwo = new ScannerCardEntry().resultTwo();
            if (resultTwo.equalsIgnoreCase("Yes")) {
                if (bankAccount.getCreditCard().getInvoiceAmount() >= bankAccount.getCredit().getPaymentPerMonth()) {//на
                    // счету должно быть больше или равно с количеством оплаты
                    LoanPaymentCalculator loanPaymentCalculator = new LoanPaymentCalculator();
                    loanPaymentCalculator.methodPay(bankAccount);
                    System.out.println("you paid");
                } else {
                    System.out.println("you don't have enough money on your card");
                }

            }


        } else if (result.equals("3")) {
            System.out.println("number of months left = " + bankAccount.getCredit().getCountMonthsToPay());
            System.out.println("debt paid = " + bankAccount.getCredit().getPaid());
            System.out.println("how much more do you have to pay = " + bankAccount.getCredit().getHowMuchToPay());
            System.out.println("how much more do you have to pay month = " + bankAccount.getCredit().getPaymentPerMonth());
            System.out.println("account on the card = " + bankAccount.getCreditCard().getInvoiceAmount());
        }


    }
}
