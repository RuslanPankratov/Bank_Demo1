package bank.demo.dto;

import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.scanner.ui.UIMenuEnter;

import java.util.List;

public class MenuOne {


    public static void main(String[] args) {
        MenuOne menuOne = new MenuOne();
        menuOne.start();
    }

    void start(){
        System.out.println("please create an account");
        ListBankAccount listBankAccount = new ListBankAccount();
        UIMenuEnter uiMenuEnter = new UIMenuEnter(listBankAccount);
        uiMenuEnter.execute();
    }


}
