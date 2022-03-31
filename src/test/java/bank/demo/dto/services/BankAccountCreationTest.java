package bank.demo.dto.services;

import bank.demo.dto.dto.*;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.helper.rule.MoreThanOneCharacter;
import bank.demo.dto.helper.rule.RuleFirstNameAndLastName;
import bank.demo.dto.helper.rule.RuleMustContainOnlyLatinCharacters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountCreationTest {

    @Test
    public void createBankAccount() {
        ListBankAccount listBankAccount = create("1");

        assertEquals(listBankAccount.getBankAccountList().size(), 1);
        assertEquals(listBankAccount.getBankAccountList().get(0).getUser().getAge(), 29);
        assertEquals(listBankAccount.getBankAccountList().get(0).getCreditCard().getLogin(), "log");
    }

    @Test
    public void createBankAccountTwo() {
        ListBankAccount listBankAccount = create("2");
        TypeOfBenefits expectedResult = TypeOfBenefits.DISABILITY_ONE_TWO;
        assertEquals(listBankAccount.getBankAccountList().
                get(listBankAccount.getBankAccountList().size() - 1).getUser().getTypeOfBenefits(), expectedResult);
    }

    @Test
    public void createBankAccountThree() {
        ListBankAccount listBankAccount = create("3");

        TypeOfBenefits expectedResult = TypeOfBenefits.DISABILITY_THREE_FOUR;
        assertEquals(listBankAccount.getBankAccountList().
                get(listBankAccount.getBankAccountList().size() - 1).getUser().getTypeOfBenefits(), expectedResult);
    }

    @Test
    public void createBankAccountFour() {
        ListBankAccount listBankAccount = create("4");

        TypeOfBenefits expectedResult = TypeOfBenefits.THE_LARGE_FAMILY;
        assertEquals(listBankAccount.getBankAccountList().
                get(listBankAccount.getBankAccountList().size() - 1).getUser().getTypeOfBenefits(), expectedResult);
    }

    @Test
    public void createBankAccountFive() {
        ListBankAccount listBankAccount = create("5");

        TypeOfBenefits expectedResult = TypeOfBenefits.PENSIONER;
        assertEquals(listBankAccount.getBankAccountList().
                get(listBankAccount.getBankAccountList().size() - 1).getUser().getTypeOfBenefits(), expectedResult);
    }

    @Test
    public void createBankAccountSix() {
        ListBankAccount listBankAccount = create("6");

        TypeOfBenefits expectedResult = TypeOfBenefits.VETERAN;
        assertEquals(listBankAccount.getBankAccountList().
                get(listBankAccount.getBankAccountList().size() - 1).getUser().getTypeOfBenefits(), expectedResult);
    }

    @Test
    public void CreateAccoun(){
        ListBankAccount listBankAccount = new ListBankAccount();
        List<RuleFirstNameAndLastName> lastNames = List.of(new MoreThanOneCharacter(),
                new RuleMustContainOnlyLatinCharacters());
        BankAccountCreation bankAccountCreation = new BankAccountCreation(lastNames);
        TypeOfBenefits typeOfBenefits = bankAccountCreation.choiceOfStatus("6");
        User user = bankAccountCreation.createUser("Igor", "Pan", 29, typeOfBenefits);
        CreditCard creditCard = bankAccountCreation.createCreditCard("log", "pas", 4000);
        bankAccountCreation.createBankAccount(listBankAccount, user, creditCard);




        System.out.println(listBankAccount.getBankAccountList().get(0).getUser().getFirstName());



    }


    private ListBankAccount create(String number) {
        List<RuleFirstNameAndLastName> lastNames = List.of(new MoreThanOneCharacter(),
                new RuleMustContainOnlyLatinCharacters());
        BankAccountCreation bankAccountCreation = new BankAccountCreation(lastNames);
        ListBankAccount listBankAccount = new ListBankAccount();
        TypeOfBenefits typeOfBenefits = bankAccountCreation.choiceOfStatus(number);
        User user = bankAccountCreation.createUser("Igor", "Pan", 29, typeOfBenefits);
        CreditCard creditCard = bankAccountCreation.createCreditCard("log", "pas", 4000);
        bankAccountCreation.createBankAccount(listBankAccount, user, creditCard);
        return listBankAccount;

    }

}