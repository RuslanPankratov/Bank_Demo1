package bank.demo.dto.services;

import bank.demo.dto.dto.ListBankAccount;
import org.springframework.stereotype.Component;
@Component
public class BankResults {

    private ListBankAccount listBankAccount;

    public BankResults(ListBankAccount listBankAccount) {
        this.listBankAccount = listBankAccount;
    }

    void numberOfClients() {
        System.out.println("number of clients" + listBankAccount.getBankAccountList().size());
    }

    void expectedProfit() {

        double expected = 0f;
        for (int i = 0; i < listBankAccount.getBankAccountList().size(); i++) {

            double profit = listBankAccount.getBankAccountList().get(i).getCredit().getBankProfit();
            expected = expected + profit;
        }
    }
}
