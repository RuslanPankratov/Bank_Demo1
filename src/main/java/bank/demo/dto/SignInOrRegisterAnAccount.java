package bank.demo.dto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SignInOrRegisterAnAccount {


    public static void main(String[] args) {
            SpringApplication.run(SignInOrRegisterAnAccount.class);
//
//        try {//надо в трай ставить, чтобы можно было увидеть какая ошибка в приложении
//            CreateTable createTable = new CreateTable();
//            createTable.createTableMethod();
//            DatabaseLoading databaseLoading = new DatabaseLoading();
//            databaseLoading.action();
//            System.out.println("test");
//
//            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);//в скобках мы
//            var uiMenu = applicationContext.getBean(UIMenu.class);
//            uiMenu.startUI();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
