package bank.demo.dto.helper;

import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.CreditDTO;
import bank.demo.dto.dto.CreditCardDTO;
import bank.demo.dto.dto.UserDTO;
import bank.demo.dto.enum_class.TypeOfBenefits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SetCreditTest {


    @Test
  public void creditSet() {
        SetCredit setCredit = new SetCredit();
        UserDTO user = new UserDTO("Igor", "Vladislav", 29,TypeOfBenefits.NO_BENEFITS);
        CreditCardDTO creditCard = new CreditCardDTO(false, "log", "pas", 0,16000);
        BankAccount bankAccount = new BankAccount(user, creditCard, 1f);
        CreditDTO credit = new CreditDTO();
        bankAccount.setCredit(credit);
        setCredit.creditSet(bankAccount, 132,123,13
                ,21,13);


       assertEquals(bankAccount.getCredit().getHowMuchToPay(), 132, 0.01);

    }
}