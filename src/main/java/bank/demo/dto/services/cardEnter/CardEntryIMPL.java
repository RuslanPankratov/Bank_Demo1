package bank.demo.dto.services.cardEnter;

import bank.demo.dto.dto.ListBankAccount;

public interface CardEntryIMPL {

    void menu(ListBankAccount listBankAccount, int i);

    String getActionName();
}
