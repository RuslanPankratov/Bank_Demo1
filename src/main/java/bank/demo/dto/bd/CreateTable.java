package bank.demo.dto.bd;


import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;


@Component
public class CreateTable implements BDInterface {

    @Override
    public void action() {
        createTableMethod();
    }

    private DB connection = new DB();


    public void createTableMethod() {
        CreateTable createTable = new CreateTable();
        createTable.createUser();
        createTable.createCreditCard();
        createTable.createCredit();
        createTable.createInsurance();
        createTable.createTransactions();
    }



    void createUser() {
        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `bank`.`user` (\n" +
                    "  `iduser` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `firstName` VARCHAR(120) NOT NULL,\n" +
                    "  `lastName` VARCHAR(120) NOT NULL,\n" +
                    "  `age` INT NOT NULL,\n" +
                    "  `typeOfBenefits` VARCHAR(120) NOT NULL,\n" +
                    "  PRIMARY KEY (`iduser`));");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void createCreditCard() {
        try {
            Statement statement = connection.getConnection().createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `bank`.`creditCard` (" +
                    " `idCreditCard` INT NOT NULL AUTO_INCREMENT, " +
                    " `blocked` TINYINT NOT NULL," +
                    " `login` VARCHAR(120) NOT NULL," +
                    " `password` VARCHAR(120) NOT NULL," +
                    " `invoiceAmount` DECIMAL(19,4) NOT NULL, " +
                    " `withdrawalLimit` DECIMAL(19,4) NOT NULL," +
                    " PRIMARY KEY (`idCreditCard`));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //1 - true
    //0 - false
    //как на выключателях I/O 🙂
    //TINYINT вместо булиона
    void createCredit() {
        try {
            Statement statement = connection.getConnection().createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `bank`.`credit` (" +
                    " `idCredit` INT NOT NULL AUTO_INCREMENT," +
                    " `howMuchToPay` DECIMAL(19,4) NULL," +
                    " `percentRate` DECIMAL(19,4) NULL," +
                    " `paid` DECIMAL(19,4) NULL," +
                    " `theTotalAmountYouPay` DECIMAL(19,4) NULL," +
                    " `countMonthsToPay` DECIMAL(19,4) NULL," +
                    " `bankProfit` DECIMAL(19,4) NULL," +
                    " `howMuchIsTheLoan` DECIMAL(19,4) NULL," +
                    " `paymentPerMonth` DECIMAL(19,4) NULL," +
                    " PRIMARY KEY (`idCredit`))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createInsurance() {
        try {
            Statement statement = connection.getConnection().createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `bank`.`insurance` (" +
                    " `idInsurance` INT NOT NULL AUTO_INCREMENT," +
                    " `sumInsured` DECIMAL(19,4) NULL," +
                    " `insurancePaid` DECIMAL(19,4) NULL," +
                    " PRIMARY KEY (`idInsurance`))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createTransactions() {
        try {
            Statement statement = connection.getConnection().createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `bank`.`transactions` (" +
                    " `idInsurance` INT NOT NULL AUTO_INCREMENT," +
                    " `amount` DECIMAL(19,4) NOT NULL," +
                    " `insurancePaid` VARCHAR(120) NOT NULL," +
                    " `idUserTransactions` INT NOT NULL, " +  //будет связь с ид клиентом
                    // связать с ид клиент
                    " PRIMARY KEY (`idInsurance`))");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}




