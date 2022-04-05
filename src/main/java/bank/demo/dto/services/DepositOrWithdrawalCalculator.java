package bank.demo.dto.services;

import bank.demo.dto.bd.DB;
import bank.demo.dto.enum_class.TransactionType;
import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.Transaction;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DepositOrWithdrawalCalculator {

    private DB connection = new DB();


    public void calculator(BankAccount bankAccount, Transaction transaction) {

        if (transaction.getTransactionType().equals(TransactionType.DEPOSIT)) {
            calculatorDeposit(bankAccount, transaction.getAmount());
        } else if (transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)) {
            calculatorWithdrawal(bankAccount, transaction.getAmount());
        }

        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("UPDATE `bank`.`creditcard` SET `invoiceAmount` = '"
                    + bankAccount.getCreditCard().getInvoiceAmount() + "' WHERE (`idCreditCard` = '"
                    + bankAccount.getCreditCard().getIdCreditCard() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void calculatorDeposit(BankAccount bankAccount, double amountOfMoney) {
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() + amountOfMoney);
    }


    void calculatorWithdrawal(BankAccount bankAccount, double amountOfMoney) {
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() - amountOfMoney);

    }
}
