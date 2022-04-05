package bank.demo.dto.scanner;

import bank.demo.dto.helper.SetCredit;
import bank.demo.dto.dto.BankAccount;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerLoanCalculation {

    public void loanCalc(BankAccount bankAccount, double paymentAmountPercent, double currentPercentUser, double countMonth,
                         double percentOverpaid, double resultPaymentMonth) {

        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        if (result.equalsIgnoreCase("Yes")) {
            int attemptsToBlock = 5;
            while (!bankAccount.getCreditCard().isBlocked()) {
                System.out.println("Enter your password");
                String password = scanner.nextLine();
                if (password.equals(bankAccount.getCreditCard().getPassword())) {
                    SetCredit setCredit = new SetCredit();
                    setCredit.creditSet(bankAccount, paymentAmountPercent, currentPercentUser, countMonth, percentOverpaid,
                            resultPaymentMonth);

                    System.out.println("loan accepted");
                    break;
                } else {
                    attemptsToBlock--;
                    if (attemptsToBlock == 0) {
                        bankAccount.getCreditCard().setBlocked(true);//блокируем если исчерпаны попытки
                        System.out.println("your card is blocked");
                    }
                }
            }
        }
    }

}

