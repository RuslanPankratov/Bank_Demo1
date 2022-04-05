package bank.demo.dto;

import bank.demo.dto.bd.*;
import bank.demo.dto.config.AppConfig;
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

            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//в скобках мы
            var uiMenu = applicationContext.getBean(UIMenu.class);
            uiMenu.startUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
