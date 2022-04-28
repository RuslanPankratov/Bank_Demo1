package bank.demo.dto.bd.services;

import bank.demo.dto.domain.*;
import bank.demo.dto.repository.HibernateCredit;
import bank.demo.dto.repository.HibernateCreditCard;
import bank.demo.dto.repository.HibernateInsurance;
import bank.demo.dto.repository.HibernateUser;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BankAccountService {

    //мне надо создавать аккаунт, из всех частей
    private HibernateUser hibernateUser;
    private HibernateCreditCard hibernateCreditCard;
    private HibernateCredit hibernateCredit;
    private HibernateInsurance hibernateInsurance;



    public BankAccount getBankAccount(int id) {
           BankAccount bankAccount = new BankAccount();
          Optional<User> user = hibernateUser.findById(id);
       //   if (user != null){
        if(user.isPresent()){
            User user1 = user.get();
            bankAccount.setUser(user1);
        }

       //   }
          Optional<CreditCard> creditCard = hibernateCreditCard.findById(id);
       //   if (creditCard != null){
        if(creditCard.isPresent()) {
            bankAccount.setCreditCard(creditCard.get());
        }
        //  }
          Optional<Credit> credit = hibernateCredit.findById(id);
       //   if (credit != null){
        if(credit.isPresent()) {
            bankAccount.setCredit(credit.get());
        }
        //  }
          Optional<Insurance> insurance= hibernateInsurance.findById(id);
        //  if (insurance != null){
        if(insurance.isPresent()) {
            bankAccount.setInsurance(insurance.get());
        }
       //   }
         return bankAccount;
    }

}
