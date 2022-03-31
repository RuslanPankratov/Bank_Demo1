package bank.demo.dto.dto;

import bank.demo.dto.dto.BankAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ListBankAccount {

    private static List<BankAccount> bankAccountList = new ArrayList<>();

    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }


    public void setBankAccount(BankAccount bankAccount){
        bankAccountList.add(bankAccount);
    }



}
