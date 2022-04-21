package bank.demo.dto.services;

//import bank.demo.dto.bd.delete.DB;
import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.Insurance;

import java.sql.SQLException;
import java.sql.Statement;

public class InsuranceCalculator {
  //  private DB connection = new DB();
    private Insurance insurance;

    public InsuranceCalculator(Insurance insurance) {
        this.insurance = insurance;
    }

    public void insurance(BankAccount bankAccount, double sum, String typeInsurance) {

        if (typeInsurance.equalsIgnoreCase("house")) {
            houses(bankAccount, sum);
        } else if (typeInsurance.equalsIgnoreCase("items")) {
            items(bankAccount, sum);
        } else if (typeInsurance.equalsIgnoreCase("health")) {
            health(bankAccount, sum);
        } else if (typeInsurance.equalsIgnoreCase("car")) {
            car(bankAccount, sum);
        }

    }


    private void houses(BankAccount bankAccount, double sum) {
        calculate(bankAccount, sum, 200);
    }

    private void items(BankAccount bankAccount, double sum) {
        calculate(bankAccount, sum, 100);
    }

    private void health(BankAccount bankAccount, double sum) {
        calculate(bankAccount, sum, 110);
    }

    private void car(BankAccount bankAccount, double sum) {
        calculate(bankAccount, sum, 20);
    }


    private void calculate(BankAccount bankAccount, double sum, double percent) {
        double howMuchToPay = sum / percent;
        Insurance insurance = new Insurance(0);
        bankAccount.setInsurance(insurance);
        bankAccount.getInsurance().setSumInsured(bankAccount.getInsurance().getSumInsured() + sum);
        bankAccount.getInsurance().setInsurancePaid(bankAccount.getInsurance().getInsurancePaid() + howMuchToPay);


      //  db(bankAccount);
    }


//    void db(BankAccount bankAccount){
//        try {
//            Statement statement = connection.getConnection().createStatement();
//            statement.executeUpdate("UPDATE `bank`.`insurance` SET `sumInsured`= "
//                    + bankAccount.getInsurance().getSumInsured() + ",`insurancePaid`="
//                    + bankAccount.getInsurance().getInsurancePaid() + " WHERE `idInsurance`="
//                    + bankAccount.getInsurance().getIdInsurance() + "; ");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
