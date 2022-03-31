package bank.demo.dto.scanner.ui;

import bank.demo.dto.config.AppConfig;
import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.CreditCard;
import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.dto.User;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.helper.rule.MoreThanOneCharacter;
import bank.demo.dto.helper.rule.RuleFirstNameAndLastName;
import bank.demo.dto.helper.rule.RuleMustContainOnlyLatinCharacters;
import bank.demo.dto.scanner.ScannerSignInOrRegisterAnAccount;
import bank.demo.dto.services.BankAccountCreation;
import bank.demo.dto.services.CardEntry;
import bank.demo.dto.services.cardEnter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
@Order(1)
@Component
public class UIMenuEnter implements UIAction {

    private ListBankAccount listBankAccount;

    @Autowired
    public UIMenuEnter(ListBankAccount listBankAccount) {
        this.listBankAccount = listBankAccount;
    }

//если нет в конструкторе чего-то, это надо записать самому, он автоматически записывает только там, где есть конструктор

    //и тут делаем под скл
    @Override
    public void execute() {
        List<RuleFirstNameAndLastName> lastNames = List.of(new MoreThanOneCharacter(),
                new RuleMustContainOnlyLatinCharacters());
        BankAccountCreation bankAccountCreation = new BankAccountCreation(lastNames);
        TypeOfBenefits typeOfBenefits = bankAccountCreation.choiceOfStatus("3");
        User user = bankAccountCreation.createUser("Igor","Pan", 29, typeOfBenefits);
        CreditCard creditCard = bankAccountCreation.createCreditCard("log","pas", 40000);
        BankAccount bankAccount = new BankAccount(user,creditCard);



        TypeOfBenefits typeOfBenefits1 = bankAccountCreation.choiceOfStatus("3");
        User user1 = bankAccountCreation.createUser("Igor","Pan", 29, typeOfBenefits1);
        CreditCard creditCard1 = bankAccountCreation.createCreditCard("log1","pas1", 40000);
        BankAccount bankAccount1 = new BankAccount(user1,creditCard1);

        listBankAccount = new ListBankAccount();
        listBankAccount.getBankAccountList().add(bankAccount);
        listBankAccount.getBankAccountList().add(bankAccount1);

       // CardEntry cardEntry = new CardEntry();
       // cardEntry.setListBankAccount(listBankAccount);




//        List<CardEntryIMPL> cardEntryIMPLS = List.of(new CreditCardEntryIMPL(), без спринка должно быть
//                new DepositAndWithdrawalCardEntryIMPL(),
//                new EditingCardEntryIMPL(),
//                new InsuranceCardEntryIMPL());
//        cardEntry.setCardEntryIMPLS(cardEntryIMPLS);


        while (true) {

            //       ScannerSignInOrRegisterAnAccount scannerSignInOrRegisterAnAccount = new ScannerSignInOrRegisterAnAccount();
            //      String result = scannerSignInOrRegisterAnAccount.result();


//            String enter = scannerSignInOrRegisterAnAccount.enter();
//            if (enter.equalsIgnoreCase("no")) {
//                break;
//            }


            //    if (result.equals("1")) {
            //      CardEntry cardEntry1 = new CardEntry(listBankAccount);
            ScannerSignInOrRegisterAnAccount scannerSignInOrRegisterAnAccount = new ScannerSignInOrRegisterAnAccount();
            while (true) {
                String enter = scannerSignInOrRegisterAnAccount.enter();
                if (enter.equalsIgnoreCase("no")) {
                    break;
                }
                System.out.println("Enter login and password");
                String log = scannerSignInOrRegisterAnAccount.scannerLogin();
                String pas = scannerSignInOrRegisterAnAccount.scannerPassword();

                ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//в скобках мы
                // указываем на класс, который имеет конфигурации и скан моего проекта
                CardEntry cardEntryIMPL = applicationContext.getBean(CardEntry.class); //тут должен быть имплемент а не интерфейс
                cardEntryIMPL.enter(log,pas);

          //      cardEntry.enter(log, pas);//error, attempts left before blocking 4
                //     }

//            } else if (result.equals("2")) {
//                List<RuleFirstNameAndLastName> lastNames = List.of(new MoreThanOneCharacter(),
//                        new RuleMustContainOnlyLatinCharacters());
//                BankAccountCreation bankAccountCreation = new BankAccountCreation(lastNames);
//                bankAccountCreation.createBankAccount(listBankAccount);
//            }


//            System.out.println("Enter login and password");
//            String log = scannerSignInOrRegisterAnAccount.scannerLogin();
//            String pas = scannerSignInOrRegisterAnAccount.scannerPassword();
//            cardEntry.enter(log, pas);
            }
        }

    }
        @Override
        public String getActionName () {
            return "Enter account";
        }
    }

