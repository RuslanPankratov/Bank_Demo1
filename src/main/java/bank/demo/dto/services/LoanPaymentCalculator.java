package bank.demo.dto.services;

//import bank.demo.dto.bd.delete.DB;
import bank.demo.dto.dto.BankAccount;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

@Component
public class LoanPaymentCalculator {
   // private DB connection = new DB();


    public void methodPay(BankAccount bankAccount) {
        System.out.println("number of months left = " + bankAccount.getCredit().getCountMonthsToPay());
        System.out.println("debt paid = " + bankAccount.getCredit().getPaid());
        System.out.println("how much more do you have to pay =  " + bankAccount.getCredit().getHowMuchToPay());
        System.out.println("account on the card = " + bankAccount.getCreditCard().getInvoiceAmount());


        bankAccount.getCredit().setCountMonthsToPay(bankAccount.getCredit().getCountMonthsToPay() - 1);
        double paidMonth = bankAccount.getCredit().getPaymentPerMonth();
        bankAccount.getCredit().setPaid(bankAccount.getCredit().getPaid() + paidMonth);
        bankAccount.getCredit().setHowMuchToPay(bankAccount.getCredit().getHowMuchToPay() - paidMonth);
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() - paidMonth);

        System.out.println("----------------------");
        System.out.println("number of months left = " + bankAccount.getCredit().getCountMonthsToPay());
        System.out.println("debt paid = " + bankAccount.getCredit().getPaid());
        System.out.println("how much more do you have to pay = " + bankAccount.getCredit().getHowMuchToPay());
        System.out.println("account on the card = " + bankAccount.getCreditCard().getInvoiceAmount());

       // bd(bankAccount);

    }

//    void bd(BankAccount bankAccount){
//        try {
//            Statement statement = connection.getConnection().createStatement();
//            statement.executeUpdate("UPDATE `bank`.`creditcard` SET `invoiceAmount` = '"
//                    + bankAccount.getCreditCard().getInvoiceAmount() + "' WHERE (`idCreditCard` = '"
//                    + bankAccount.getCreditCard().getIdCreditCard() + "');");
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }
}
