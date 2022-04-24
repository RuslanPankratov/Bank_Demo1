package bank.demo.dto.services;

import bank.demo.dto.dto.*;
import bank.demo.dto.enum_class.TransactionType;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.helper.rule.MoreThanOneCharacter;
import bank.demo.dto.helper.rule.RuleFirstNameAndLastName;
import bank.demo.dto.helper.rule.RuleMustContainOnlyLatinCharacters;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DepositOrWithdrawalCalculatorTest {

    @Test
    public void calculatorTest (){
        BankAccount bankAccount = create();
        DepositOrWithdrawalCalculator depositOrWithdrawalCalculator = new DepositOrWithdrawalCalculator();
        TransactionDTO transaction = new TransactionDTO(20000, TransactionType.DEPOSIT);
        depositOrWithdrawalCalculator.calculator(bankAccount,transaction);
        assertEquals(bankAccount.getCreditCard().getInvoiceAmount(), 20000);

        TransactionDTO transaction1 = new TransactionDTO(3000, TransactionType.WITHDRAWAL);
        depositOrWithdrawalCalculator.calculator(bankAccount,transaction1);
        assertEquals(bankAccount.getCreditCard().getInvoiceAmount(), 17000);

    }



    private BankAccount create(){
        List<RuleFirstNameAndLastName> lastNames = List.of(new MoreThanOneCharacter(),
                new RuleMustContainOnlyLatinCharacters());
        BankAccountCreation bankAccountCreation = new BankAccountCreation(lastNames);
        TypeOfBenefits typeOfBenefits = bankAccountCreation.choiceOfStatus("3");
        UserDTO user = bankAccountCreation.createUser("Igor","Pan", 29, typeOfBenefits);
        CreditCardDTO creditCard = bankAccountCreation.createCreditCard("log","pas", 4000);
        BankAccount bankAccount = new BankAccount(user,creditCard, 0);
        return bankAccount;
    }

}