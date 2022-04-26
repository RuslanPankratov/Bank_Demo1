package bank.demo.dto.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListBankAccount {

    private static List<BankAccount> bankAccountList = new ArrayList<>();

    public  List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    public  void setBankAccountList(List<BankAccount> bankAccountList) {
        ListBankAccount.bankAccountList = bankAccountList;
    }
}
