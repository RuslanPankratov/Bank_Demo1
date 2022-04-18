package bank.demo.dto.helper;

import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.Credit;
import bank.demo.dto.dto.CreditCard;
import bank.demo.dto.dto.Users1;
import bank.demo.dto.enum_class.TypeOfBenefits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SetCreditTest {


    @Test
  public void creditSet() {
        SetCredit setCredit = new SetCredit();
        Users1 user = new Users1("Igor", "Vladislav", 29,TypeOfBenefits.NO_BENEFITS);
        CreditCard creditCard = new CreditCard(false, "log", "pas", 0,16000);
        BankAccount bankAccount = new BankAccount(user, creditCard, 1f);
        Credit credit = new Credit();
        bankAccount.setCredit(credit);
        setCredit.creditSet(bankAccount, 132,123,13
                ,21,13);


       assertEquals(bankAccount.getCredit().getHowMuchToPay(), 132, 0.01);

    }
}