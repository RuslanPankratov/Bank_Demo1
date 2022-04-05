package bank.demo.dto.services;

import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.services.cardEnter.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class CardEntry {

    private ListBankAccount listBankAccount;
    private final List<CardEntryIMPL> cardEntryIMPLS;

    public CardEntry(ListBankAccount listBankAccount, List<CardEntryIMPL> cardEntryIMPLS) {
        this.listBankAccount = listBankAccount;
        this.cardEntryIMPLS = cardEntryIMPLS;
    }

    public void enter(String login, String password) {
        int countBlock = 5;
        while (true) {
            for (int i = 0; i < listBankAccount.getBankAccountList().size(); i++) {
                String loginAccount = listBankAccount.getBankAccountList().get(i).getCreditCard().getLogin();
                String passwordAccount = listBankAccount.getBankAccountList().get(i).getCreditCard().getPassword();

                if (loginAccount.equals(login)) {
                    if (passwordAccount.equals(password)) {
                        enterCard(i, countBlock);
                    } else {
                        countBlock--;
                        System.out.println("error, attempts left before blocking " + countBlock); //если будет 0, тогда будет блок
                        if (countBlock == 0) {
                            listBankAccount.getBankAccountList().get(i).getCreditCard().setBlocked(true);//будет заблокирована карта
                            System.out.println("your card is blocked");
                        }
                    }


                }
            }
        }

    }


    void enterCard(int i, int countBlock) {

        if (!listBankAccount.getBankAccountList().get(i).getCreditCard().isBlocked()) {
            countBlock = 5;

            try {
                for (int j = 0; j < cardEntryIMPLS.size(); j++) {
                    System.out.println(j + 1 + ": " + cardEntryIMPLS.get(j).getActionName());
                }
                Scanner scanner = new Scanner(System.in);
                int numberMenu = scanner.nextInt();
                cardEntryIMPLS.get(numberMenu - 1).menu(listBankAccount, i);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("your card is blocked");
        }
    }

    public ListBankAccount getListBankAccount() {
        return listBankAccount;
    }

    public void setListBankAccount(ListBankAccount listBankAccount) {
        this.listBankAccount = listBankAccount;
    }
}
