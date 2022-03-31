package bank.demo.dto.services;

import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.services.cardEnter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CardEntry {//вход на карту

    private ListBankAccount listBankAccount;
    private final List<CardEntryIMPL> cardEntryIMPLS;

    public CardEntry(ListBankAccount listBankAccount, List<CardEntryIMPL> cardEntryIMPLS) {
        this.listBankAccount = listBankAccount;
        this.cardEntryIMPLS = cardEntryIMPLS;
    }

//    public CardEntry(ListBankAccount listBankAccount) {
//        this.listBankAccount = listBankAccount;
//    }

    //5 ошибок, блокировка аккаунта, если вводился логин, но пароль был не правильный

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


    void enterCard(int i, int countBlock) { //здесь основное меню которое будет когда у нас уже есть аккаунт
      //  cardEntryIMPLS = List.of(new DepositAndWithdrawalCardEntryIMPL(), new CreditCardEntryIMPL()
        //        , new InsuranceCardEntryIMPL(), new EditingCardEntryIMPL());//тут



        if (!listBankAccount.getBankAccountList().get(i).getCreditCard().isBlocked()) {
            countBlock = 5;
          //  while (true) {
//тут пытаемся всё сделать через имплемент, попробуем сделать это без спринга, а потом с ним
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

                // отредактировать, чтобы нельзя было ошибаться с паролем

                //            } else {
                //в случае 5 раз подряд ошибки набирания пароля, будет заблокирован аккаунт
//                    System.out.println("you have selected the wrong menu");
//                }

        } else {
            System.out.println("your card is blocked");
        }
    }


   // public List<CardEntryIMPL> getCardEntryIMPLS() {
//        return cardEntryIMPLS;
//    }


    public ListBankAccount getListBankAccount() {
        return listBankAccount;
    }

    public void setListBankAccount(ListBankAccount listBankAccount) {
        this.listBankAccount = listBankAccount;
    }
}
