package bank.demo.dto.services;

//import bank.demo.dto.bd.delete.DB;
import bank.demo.dto.enum_class.TransactionType;
import bank.demo.dto.dto.BankAccountDTO;
import bank.demo.dto.dto.TransactionDTO;
import org.springframework.stereotype.Component;

@Component
public class DepositOrWithdrawalCalculator {

 //   private DB connection = new DB();


    public void calculator(BankAccountDTO bankAccount, TransactionDTO transaction) {

        if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
            calculatorDeposit(bankAccount, transaction.getAmount());
        } else if (transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
            calculatorWithdrawal(bankAccount, transaction.getAmount());
        }

      //  bd(bankAccount);
    }

    void calculatorDeposit(BankAccountDTO bankAccount, double amountOfMoney) {
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() + amountOfMoney);
    }


    void calculatorWithdrawal(BankAccountDTO bankAccount, double amountOfMoney) {
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() - amountOfMoney);

    }

//    void bd(BankAccount bankAccount){
//        try {
//            Statement statement = connection.getConnection().createStatement();
//            statement.executeUpdate("UPDATE `bank`.`creditcard` SET `invoiceAmount` = '"
//                    + bankAccount.getCreditCard().getInvoiceAmount() + "' WHERE (`idCreditCard` = '"
//                    + bankAccount.getCreditCard().getIdCreditCard() + "');");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
