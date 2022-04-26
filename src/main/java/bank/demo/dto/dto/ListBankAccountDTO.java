package bank.demo.dto.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ListBankAccountDTO {

    private static List<BankAccountDTO> bankAccountList = new ArrayList<>();

    public List<BankAccountDTO> getBankAccountList() {
        return bankAccountList;
    }


    public void setBankAccount(BankAccountDTO bankAccount) {
        bankAccountList.add(bankAccount);
    }


}
