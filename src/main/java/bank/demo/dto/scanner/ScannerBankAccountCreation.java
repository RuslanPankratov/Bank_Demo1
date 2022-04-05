package bank.demo.dto.scanner;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerBankAccountCreation {


    public String scannerFirstName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your first name");
        String firstName = scanner.nextLine();
        return firstName;
    }

    public String scannerLastName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your last name");
        String lastName = scanner.nextLine();
        return lastName;
    }

    public int scannerAge() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your full age");
        int age = scanner.nextInt();
        return age;
    }

    public String scannerType() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Push your status");
        System.out.println("1: NO_BENEFITS");
        System.out.println("2:  DISABILITY_ONE_TWO");
        System.out.println("3: DISABILITY_THREE_FOUR");
        System.out.println("4: THE_LARGE_FAMILY");
        System.out.println("5: PENSIONER");
        System.out.println("6:  VETERAN");
        String type = scanner1.nextLine();
        return type;
    }

    public String scannerLogin() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter your login");
        String login = scanner1.nextLine();
        return login;
    }

    public String scannerPassword() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter your password");
        String password = scanner1.nextLine();
        return password;
    }

    public double scannerLimit() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter enter a limit on withdrawing money at a time");
        double limit = scanner1.nextDouble();
        return limit;
    }


}
