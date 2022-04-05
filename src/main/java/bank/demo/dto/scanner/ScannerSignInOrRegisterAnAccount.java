package bank.demo.dto.scanner;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerSignInOrRegisterAnAccount {
    Scanner scanner = new Scanner(System.in);

    public String result() {
        System.out.println(
                "Enter or register?\n" +
                        "1: login\n" +
                        "2: register");

        String result = scanner.nextLine();
        return result;
    }

    public String enter() {
        System.out.println("Do you want to continue?Yes or no?");
        String enter = scanner.nextLine();
        return enter;
    }

    public String scannerLogin() {
        System.out.println("Enter your login");
        String login = scanner.nextLine();
        return login;
    }

    public String scannerPassword() {
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        return password;
    }


}
