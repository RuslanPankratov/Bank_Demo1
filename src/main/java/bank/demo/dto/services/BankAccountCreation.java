package bank.demo.dto.services;

import bank.demo.dto.bd.DB;
import bank.demo.dto.dto.*;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.helper.rule.RuleFirstNameAndLastName;
import bank.demo.dto.scanner.ScannerBankAccountCreation;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


@Component
public class BankAccountCreation {
    private DB connection = new DB();
    private List<RuleFirstNameAndLastName> ruleFirstNameAndLastName;

    public BankAccountCreation(List<RuleFirstNameAndLastName> ruleFirstNameAndLastName) {
        this.ruleFirstNameAndLastName = ruleFirstNameAndLastName;
    }

    public void createBankAccount(ListBankAccount bankAccountList) {
        //надо тут сделать всё под скл
        //подработать с тем, если вышла ошибка, чтобы занова всё было
        ScannerBankAccountCreation scannerBankAccountCreation = new ScannerBankAccountCreation();
        String firstName = "";
        String lastName = "";

        try {

            firstName = scannerBankAccountCreation.scannerFirstName();
            lastName = scannerBankAccountCreation.scannerLastName();

            for (int i = 0; i < ruleFirstNameAndLastName.size(); i++) {
                ruleFirstNameAndLastName.get(i).rule(firstName);
                ruleFirstNameAndLastName.get(i).rule(lastName);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        int age = scannerBankAccountCreation.scannerAge();
        String type = scannerBankAccountCreation.scannerType();

        TypeOfBenefits typeOfBenefits = choiceOfStatus(type);
        User user = createUser(firstName, lastName, age, typeOfBenefits);

        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `bank`.`user` (`firstName`, `lastName`, `age`, `typeOfBenefits`) VALUES ('" +
                    firstName + "', '" + lastName + "', '" + age + "','" + type + "');");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        int idUser = 0;
        try {//
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.user ORDER BY iduser DESC LIMIT 1;");
            if (resultSet.next()) { //если я хочу, что-то достать, обязательно надо использовать ресулт некст
                idUser = resultSet.getInt("iduser");
                if (idUser > 0) {
                    user.setIdUser(resultSet.getInt("iduser"));
                    System.out.println(user.getIdUser() + " id user");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String login = scannerBankAccountCreation.scannerLogin();
        String password = scannerBankAccountCreation.scannerPassword();
        double limit = scannerBankAccountCreation.scannerLimit();

        CreditCard creditCard = createCreditCard(login, password, limit);
        creditCard.setIdCreditCard(idUser);
        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `bank`.`creditcard` (`login`, `password`, `withdrawalLimit`, `blocked`, `invoiceAmount`) VALUES ('" +
                    login + "', '" + password + "', '" + limit + "','0', '0');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createBankAccount(bankAccountList, user, creditCard, idUser);
    }


    void createBankAccount(ListBankAccount bankAccountList, User user, CreditCard creditCard, int idUser) {

        BankAccount bankAccount = new BankAccount(user, creditCard);
        bankAccount.setClientId(idUser);
        Credit credit = createCredit();
        credit.setIdCredit(idUser);
        bankAccount.setCredit(credit);
        Insurance insurance = createInsurance();
        insurance.setIdInsurance(idUser);
        bankAccount.setInsurance(insurance);
        bankAccount.setClientId(bankAccount.getUser().getIdUser());
        bankAccountList.getBankAccountList().add(bankAccount);
    }

    private Credit createCredit() {
        Credit credit = new Credit();
        credit.setHowMuchToPay(0);
        credit.setPercentRate(0);
        credit.setPaid(0);
        credit.setTheTotalAmountYouPay(0);
        credit.setCountMonthsToPay(0);
        credit.setBankProfit(0);
        credit.setHowMuchIsTheLoan(0);
        credit.setPaymentPerMonth(0);

        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `bank`.`credit` (`howMuchToPay`, `percentRate`, `paid`," +
                    " `theTotalAmountYouPay`, `countMonthsToPay`, `bankProfit`, `howMuchIsTheLoan`, `paymentPerMonth`) " +
                    "VALUES ('0','0','0','0','0','0','0','0');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credit;
    }


    private Insurance createInsurance() {
        Insurance insurance = new Insurance(0);
        insurance.setInsurancePaid(0);

        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `bank`.`insurance` (`sumInsured`, `insurancePaid`) " +
                    "VALUES ('0','0');");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return insurance;
    }

    public User createUser(String firstName, String lastName, int age, TypeOfBenefits typeOfBenefits) {
        User user = new User(firstName, lastName, age, typeOfBenefits);
        return user;
    }

    public CreditCard createCreditCard(String login, String password, double limit) {
        CreditCard creditCard = new CreditCard(false, login, password, 0, limit);
        return creditCard;
    }

    public TypeOfBenefits choiceOfStatus(String type) {
        if (type.equalsIgnoreCase("2")) {
            return TypeOfBenefits.DISABILITY_ONE_TWO;
        } else if (type.equalsIgnoreCase("3")) {
            return TypeOfBenefits.DISABILITY_THREE_FOUR;
        } else if (type.equalsIgnoreCase("4")) {
            return TypeOfBenefits.THE_LARGE_FAMILY;
        } else if (type.equalsIgnoreCase("5")) {
            return TypeOfBenefits.PENSIONER;
        } else if (type.equalsIgnoreCase("6")) {
            return TypeOfBenefits.VETERAN;
        }
        return TypeOfBenefits.NO_BENEFITS;
    }
}
