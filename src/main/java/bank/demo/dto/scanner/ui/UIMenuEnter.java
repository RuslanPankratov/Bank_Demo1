package bank.demo.dto.scanner.ui;

import bank.demo.dto.config.AppConfig;
import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.scanner.ScannerSignInOrRegisterAnAccount;
import bank.demo.dto.services.CardEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
public class UIMenuEnter implements UIAction {

    private ListBankAccount listBankAccount;

    @Autowired
    public UIMenuEnter(ListBankAccount listBankAccount) {
        this.listBankAccount = listBankAccount;
    }

//если нет в конструкторе чего-то, это надо записать самому, он автоматически записывает только там, где есть конструктор
    @Override
    public void execute() {

        while (true) {
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
                cardEntryIMPL.enter(log, pas);
            }
        }
    }

    @Override
    public String getActionName() {
        return "Enter account";
    }
}

