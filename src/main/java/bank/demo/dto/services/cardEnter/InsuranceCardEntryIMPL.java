package bank.demo.dto.services.cardEnter;

import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.scanner.ScannerCardEntry;
import bank.demo.dto.services.InsuranceCalculator;
import bank.demo.dto.services.PayForInsurance;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Order(3)
@Component
public class InsuranceCardEntryIMPL implements CardEntryIMPL{

    @Override
    public void menu(ListBankAccount listBankAccount, int i) {
        insurance(listBankAccount,i);
    }
    @Override
    public String getActionName() {
        return "Insurance";
    }



    void insurance(ListBankAccount listBankAccount, int i) {
        while (true) {
            ScannerCardEntry scannerCardEntry = new ScannerCardEntry();
            InsuranceCalculator insuranceCalculator = new InsuranceCalculator(
                    listBankAccount.getBankAccountList().get(i).getInsurance());
            String type = scannerCardEntry.methodType();
            double sum = scannerCardEntry.methodSum();
            insuranceCalculator.insurance(listBankAccount.getBankAccountList().get(i), sum, type);
            String answer = scannerCardEntry.methodAnswer(listBankAccount, i);
            if (answer.equalsIgnoreCase("yes")) {
                PayForInsurance payForInsurance = new PayForInsurance();
                payForInsurance.payInsurance(listBankAccount.getBankAccountList().get(i));
                 listBankAccount.getBankAccountList().get(i).getCreditCard().setInvoiceAmount( listBankAccount.
                         getBankAccountList().get(i).getCreditCard().getInvoiceAmount() -
                         listBankAccount.getBankAccountList().get(i).getInsurance().getInsurancePaid());
                        System.out.println("invoice amount : " + listBankAccount.getBankAccountList().get(i).
                                getCreditCard().getInvoiceAmount());
                System.out.println("exit");
                break;
            }
        }

    }
}
