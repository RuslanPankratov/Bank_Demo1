package bank.demo.dto.scanner;

import bank.demo.dto.dto.ListBankAccount;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerCardEntry {

    Scanner scanner = new Scanner(System.in);

    public String methodType() {
        System.out.println("specify the type of insurance");
        System.out.println("house");
        System.out.println("items");
        System.out.println("health");
        System.out.println("car");

        String type = scanner.nextLine();
        return type;
    }


    public double methodSum() {

        System.out.println("enter the amount of the insurance object");
        double sum = scanner.nextDouble();
        return sum;
    }

    public String methodAnswer(ListBankAccount listBankAccount, int i) {
        System.out.println("insurance for the amount = "
                + listBankAccount.getBankAccountList().get(i).getInsurance().getSumInsured());

        System.out.println("how much to pay = " + listBankAccount.
                getBankAccountList().get(i).getInsurance().getInsurancePaid());
        System.out.println("you want pay insurance? Yes or No?");
        Scanner scanner1 = new Scanner(System.in);
        String answer = scanner1.nextLine();
        return answer;
    }

    public String methodResult() {
        System.out.println("what do you want to edit?");
        System.out.println("1: first and last name");
        System.out.println("2: status");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        return result;
    }


    public String methodFirstName() {
        System.out.println("Enter your name");
        Scanner scanner = new Scanner(System.in);
        String firstName = scanner.nextLine();
        return firstName;
    }

    public String methodLastName() {
        System.out.println("Enter your last name");
        Scanner scanner = new Scanner(System.in);
        String lastName = scanner.nextLine();
        return lastName;
    }

    public String methodResultType() {
        System.out.println("Select your status");
        System.out.println("  1: NO_BENEFITS,\n" +
                "  2:  DISABILITY_ONE_TWO,\n" +
                "  3:  DISABILITY_THREE_FOUR,\n" +
                "  4:  THE_LARGE_FAMILY,\n" +
                "  5:  PENSIONER,\n" +
                "  6:  VETERAN");
        Scanner scanner = new Scanner(System.in);
        String resultType = scanner.nextLine();
        return resultType;
    }

    public String resultWithdrawalOrDeposit() {
        Scanner scanner = new Scanner(System.in);
        //разделим выбор между депозитом и снятием денег
        System.out.println("1: withdrawal");
        System.out.println("2: deposit");
        String resultWithdrawalOrDeposit = scanner.nextLine();
        return resultWithdrawalOrDeposit;
    }


    public double enterAmount(ListBankAccount listBankAccount, int i) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter amount");
        System.out.println("on account" + listBankAccount.getBankAccountList().get(i).getCreditCard().getInvoiceAmount());
        double summa = scanner.nextDouble();
        return summa;
    }

    public double scannerDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter amount");
        double summa = scanner.nextDouble();
        return summa;
    }

    public String scannerMethodCredit() {
        System.out.println("1: how much i want to borrow");
        System.out.println("2: manual payment ");
        System.out.println("3: current loan information");
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        return result;
    }

    public double scannerCredit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("how much do you want to take?");//какую вы сумму хотите взять?
        double credit = scanner.nextDouble();
        return credit;
    }

    public double scannerMonth() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("how many months do you want to pay");
        double month = scanner.nextDouble();
        return month;
    }


    public String resultTwo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("do you want to pay off the loan? Yes or No?");
        //проверка сколько должна быть сумма на уплату кредита
        String resultTwo = scanner.nextLine();
        return resultTwo;
    }

}
