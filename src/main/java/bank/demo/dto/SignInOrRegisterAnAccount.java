package bank.demo.dto;

import bank.demo.dto.bd.*;
import bank.demo.dto.config.AppConfig;
import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.scanner.ui.UIMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SignInOrRegisterAnAccount {


    public static void main(String[] args) {

        try {//надо в трай ставить, чтобы можно было увидеть какая ошибка в приложении
            CreateTable createTable = new CreateTable();
            createTable.createTableMethod();
            DatabaseLoading databaseLoading = new DatabaseLoading();
            databaseLoading.action();
            System.out.println("test");

            //если не существует скл страница, то создаём таблицы и столбики
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//в скобках мы
          //   указываем на класс, который имеет конфигурации и скан моего проекта
//            BDActions bdActions = applicationContext.getBean(BDActions.class);
//            bdActions.action();
            var uiMenu = applicationContext.getBean(UIMenu.class);//здесь мы будем получить те бины, которые сканер
            // смог получить, этот класс является контейнером
            uiMenu.startUI();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
