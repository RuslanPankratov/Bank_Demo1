package bank.demo.dto.bd.services;


import bank.demo.dto.domain.*;
import bank.demo.dto.repository.HibernateCreditCard;
import bank.demo.dto.repository.HibernateUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class BankService {

    //можно собирать лист из бд, чтобы по ид добавлялось всё, и не все поля были обязательными, тогда можно будет
    // редактировать постепенно

   private ListBankAccount listBankAccount; //если что добавить значение

    //теперь мне надо место, которое будет доставать все бд и загружать данные

    //я беру листы и буду сравнивать по ид, если нет ид, то создаю банк аккаунт и добавляю в список, если ид есть, то под этот ид мы добавляем другие компоненты
   private HibernateUser hibernateUser;
  private HibernateCreditCard creditCard;



  public List<BankAccount> loadBankAccount(){
    List<User> users = hibernateUser.findAll();
 //   List<CreditCard> creditCards = creditCard.findAll();






//    if(listBankAccount.getBankAccountList().size() == 0){
//        BankAccount bankAccount = new BankAccount();
//        bankAccount.setId(1);
//        listBankAccount.getBankAccountList().add(bankAccount);
//    } //теперь у нас есть первый банк аккаунт
       //дальше проверка на сравнение ид

      loadUser(users);
     // loadCreditCard(creditCards);


       return listBankAccount.getBankAccountList();
   }


   void loadUser(List<User> users){
       for (int i = 0; i < users.size(); i++) {
           BankAccount bankAccount = new BankAccount();
           bankAccount.setUser(users.get(i));
           bankAccount.setId(users.get(i).getIdUser());
           listBankAccount.getBankAccountList().add(bankAccount);
       }
   }


   void loadCreditCard(List<CreditCard> creditCards){


       for (int i = 0; i < creditCards.size(); i++) {
           //подумай нужна проверка на то есть ли ид банка, если нет, тогда создать его
           int countList = 0;
           for (int j = 0; j < listBankAccount.getBankAccountList().size(); j++) {
               if (listBankAccount.getBankAccountList().get(j).getId() == creditCards.get(i).getIdCreditCard()){
                   listBankAccount.getBankAccountList().get(j).setCreditCard(creditCards.get(i)); //устанавливаем юзера, если ид совпал
                   // listBankAccount.getBankAccountList().get(j).setId(creditCards.get(i).getIdUser());

               } else if (listBankAccount.getBankAccountList().get(j).getId() != creditCards.get(i).getIdCreditCard()) {
                   countList++;
                   if (countList == listBankAccount.getBankAccountList().size()) {
                       BankAccount bankAccount = new BankAccount();
                       bankAccount.setCreditCard(creditCards.get(i));
                       bankAccount.setId(creditCards.get(i).getIdCreditCard());
                   }
               }
           }
       }

   }
}
