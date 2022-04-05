package bank.demo.dto.services.cardEnter;

import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.dto.Transaction;
import bank.demo.dto.enum_class.TransactionType;
import bank.demo.dto.scanner.ScannerCardEntry;
import bank.demo.dto.services.DepositOrWithdrawalCalculator;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Order(1)
@Component
public class DepositAndWithdrawalCardEntryIMPL implements CardEntryIMPL {

    @Override
    public void menu(ListBankAccount listBankAccount, int i) {
        ScannerCardEntry scannerCardEntry = new ScannerCardEntry();
        String resultWithdrawalOrDeposit = scannerCardEntry.resultWithdrawalOrDeposit();
        DepositOrWithdrawalCalculator depositOrWithdrawalCalculator =
                new DepositOrWithdrawalCalculator();

        if (resultWithdrawalOrDeposit.equalsIgnoreCase("1")) {
            //тут будет снятие денег
            double summa = scannerCardEntry.enterAmount(listBankAccount, i);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your password");
            String pas = scanner.nextLine();
            if (pas.equals(listBankAccount.getBankAccountList().get(i).getCreditCard().getPassword())) {
                if (summa < listBankAccount.getBankAccountList().get(i).getCreditCard().getWithdrawalLimit()) {
                    withdrawal(listBankAccount, summa, i, depositOrWithdrawalCalculator);
                } else {
                    System.out.println("you cannot withdraw that amount per day");
                    //когда превышен дневной лимит
                }
            }

        } else if (resultWithdrawalOrDeposit.equalsIgnoreCase("2")) {
            // а тут ложим деньги
            deposit(listBankAccount, depositOrWithdrawalCalculator, i);
        }
    }


    private void withdrawal(ListBankAccount listBankAccount, double summa, int i, DepositOrWithdrawalCalculator depositOrWithdrawalCalculator) {
        if (summa < listBankAccount.getBankAccountList().get(i).getCreditCard().getInvoiceAmount()) {//сумма снятия денег не должна быть больше чем счёт
            Transaction transaction = new Transaction(summa, TransactionType.WITHDRAWAL);
            depositOrWithdrawalCalculator.calculator(listBankAccount.getBankAccountList().get(i), transaction);
            System.out.println("on account" + listBankAccount.getBankAccountList().get(i).getCreditCard().getInvoiceAmount());
        } else {
            System.out.println("you want to withdraw more than you have on your account");
            //если попытка снятия денег больше, чем сама сума на карте
        }
    }

    private void deposit(ListBankAccount listBankAccount, DepositOrWithdrawalCalculator depositOrWithdrawalCalculator, int i) {
        double summa = new ScannerCardEntry().scannerDeposit();
        Transaction transaction = new Transaction(summa, TransactionType.DEPOSIT);
        depositOrWithdrawalCalculator.calculator(listBankAccount.getBankAccountList().get(i), transaction);
    }

    @Override
    public String getActionName() {
        return "Deposit And Withdrawal";
    }
}
