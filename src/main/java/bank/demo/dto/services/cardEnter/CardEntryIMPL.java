package bank.demo.dto.services.cardEnter;

import bank.demo.dto.dto.ListBankAccount;
import org.springframework.stereotype.Component;


public interface CardEntryIMPL {

    void menu(ListBankAccount listBankAccount, int i);

    String getActionName();
}
