package bank.demo.dto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class SignInOrRegisterAnAccount {


    public static void main(String[] args) {
        try {
            SpringApplication.run(SignInOrRegisterAnAccount.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        }
    }
}
