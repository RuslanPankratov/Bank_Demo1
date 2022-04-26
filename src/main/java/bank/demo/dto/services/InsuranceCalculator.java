package bank.demo.dto.services;

//import bank.demo.dto.bd.delete.DB;
import bank.demo.dto.dto.BankAccountDTO;
import bank.demo.dto.dto.InsuranceDTO;

public class InsuranceCalculator {
  //  private DB connection = new DB();
    private InsuranceDTO insurance;

    public InsuranceCalculator(InsuranceDTO insurance) {
        this.insurance = insurance;
    }

    public void insurance(BankAccountDTO bankAccount, double sum, String typeInsurance) {

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


    private void houses(BankAccountDTO bankAccount, double sum) {
        calculate(bankAccount, sum, 200);
    }

    private void items(BankAccountDTO bankAccount, double sum) {
        calculate(bankAccount, sum, 100);
    }

    private void health(BankAccountDTO bankAccount, double sum) {
        calculate(bankAccount, sum, 110);
    }

    private void car(BankAccountDTO bankAccount, double sum) {
        calculate(bankAccount, sum, 20);
    }


    private void calculate(BankAccountDTO bankAccount, double sum, double percent) {
        double howMuchToPay = sum / percent;
        InsuranceDTO insurance = new InsuranceDTO(0);
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
