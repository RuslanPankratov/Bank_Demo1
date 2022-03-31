package bank.demo.dto.services;

import bank.demo.dto.bd.DB;
import bank.demo.dto.dto.*;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.helper.rule.RuleFirstNameAndLastName;
import bank.demo.dto.scanner.ScannerBankAccountCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

@Component
public class BankAccountCreation {
    private DB connection = new DB();
    private List<RuleFirstNameAndLastName> ruleFirstNameAndLastName;

    public BankAccountCreation(List<RuleFirstNameAndLastName> ruleFirstNameAndLastName) {
        this.ruleFirstNameAndLastName = ruleFirstNameAndLastName;
    }

    //предлагаемый вариант
    //Ruslan, [31.03.2022 16:59]
    //@Override
    //public ToDoEntity save(ToDoEntity toDoEntity) {
    //    var query = "INSERT INTO todo(name, description) VALUES (?, ?)";
    //    var keyHolder = new GeneratedKeyHolder();
    //    jdbcTemplate.update(connection -> {
    //        var ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    //        ps.setString(1, toDoEntity.getName());
    //        ps.setString(2, toDoEntity.getDescription());
    //        return ps;
    //    }, keyHolder);
    //    toDoEntity.setId(keyHolder.getKey().intValue());
    //    return toDoEntity;
    //}

    public void createBankAccount(ListBankAccount bankAccountList) {
        //надо тут сделать всё под скл
        //подработать с тем, если вышла ошибка, чтобы занова всё было
        ScannerBankAccountCreation scannerBankAccountCreation = new ScannerBankAccountCreation();
        String firstName = "";
        String lastName = "";

        try {

            //тут активируем таблицу user
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

        //sql
        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `bank`.`user` (`firstName`, `lastName`, `age`, `typeOfBenefits`) VALUES ('" +
                    firstName + "', '" + lastName + "', '" + age + "','type');");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {//
            Statement statement = connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.user;");//надо изменить именно в этом
            // юзеры ид, чтобы он не рещил всё юзеры изменить
            if(resultSet.next()){ //если я хочу, что-то достать, обязательно надо использовать ресулт некст
                user.setIdUser(resultSet.getInt("iduser"));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        String login = scannerBankAccountCreation.scannerLogin();
        String password = scannerBankAccountCreation.scannerPassword();
        double limit = scannerBankAccountCreation.scannerLimit();


        CreditCard creditCard = createCreditCard(login, password, limit);
        //INSERT INTO `bank`.`creditcard` (`login`, `password`, `withdrawalLimit`) VALUES ('res10', 'res10', '1000');
//sql
        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `bank`.`creditcard` (`login`, `password`, `withdrawalLimit`, `blocked`, `invoiceAmount`) VALUES ('" +
                    login + "', '" + password + "', '" + limit + "','0', '0');");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.creditcard;");
            creditCard.setIdCreditCard(resultSet.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createBankAccount(bankAccountList, user, creditCard);
    }


    void createBankAccount(ListBankAccount bankAccountList, User user, CreditCard creditCard) {

        BankAccount bankAccount = new BankAccount(user, creditCard);
        Credit credit = createCredit();
        bankAccount.setCredit(credit);
        Insurance insurance = createInsurance();
        bankAccount.setInsurance(insurance);
        bankAccount.setClientId(bankAccount.getUser().getIdUser());
        //надо установить ид от бд
        bankAccountList.getBankAccountList().add(bankAccount);
        //добавить ид
    }

    private Credit createCredit(){
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
            statement.executeUpdate("INSERT INTO `bank`.`creditcard` (`howMuchToPay`, `percentRate`, `paid`," +
                    " `theTotalAmountYouPay`, `countMonthsToPay`, `bankProfit`, `howMuchIsTheLoan`, `paymentPerMonth`) " +
                    "VALUES ('0','0','0','0','0','0','0','0');");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.creditcard;");
            credit.setIdCredit(resultSet.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return credit;
    }


    private Insurance createInsurance(){
          Insurance insurance = new Insurance(0);
          insurance.setInsurancePaid(0);

        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("INSERT INTO `bank`.`insurance` (`sumInsured`, `insurancePaid`) " +
                    "VALUES ('0','0');");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.creditcard;");
            insurance.setIdInsurance(resultSet.getInt(1));
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