package bank.demo.dto.services;

import bank.demo.dto.bd.DB;
import bank.demo.dto.dto.BankAccount;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

@Component
public class LoanPaymentCalculator {
    private DB connection = new DB();


    public void methodPay(BankAccount bankAccount) {  //тут будет постоянное снятие денег
        System.out.println("number of months left = " + bankAccount.getCredit().getCountMonthsToPay());
        System.out.println("debt paid = " + bankAccount.getCredit().getPaid());
        System.out.println("how much more do you have to pay =  " + bankAccount.getCredit().getHowMuchToPay());
        System.out.println("account on the card = " + bankAccount.getCreditCard().getInvoiceAmount());


        bankAccount.getCredit().setCountMonthsToPay(bankAccount.getCredit().getCountMonthsToPay() - 1); //отнимаем месяц когда оплатили
        double paidMonth = bankAccount.getCredit().getPaymentPerMonth(); //на сколько в месяц надо платить
        bankAccount.getCredit().setPaid(bankAccount.getCredit().getPaid() + paidMonth); //то сколько заплатил клиент
        bankAccount.getCredit().setHowMuchToPay(bankAccount.getCredit().getHowMuchToPay() - paidMonth); //долг уменьшился
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() - paidMonth);
        //счёт когда он оплатил
        System.out.println("----------------------");
        System.out.println("number of months left = " + bankAccount.getCredit().getCountMonthsToPay());
        System.out.println("debt paid = " + bankAccount.getCredit().getPaid());
        System.out.println("how much more do you have to pay = " + bankAccount.getCredit().getHowMuchToPay());
        System.out.println("account on the card = " + bankAccount.getCreditCard().getInvoiceAmount());

        try {
            Statement statement = connection.getConnection().createStatement();
            //UPDATE `bank`.`creditcard` SET `invoiceAmount` = '12' WHERE (`idCreditCard` = '1');
            statement.executeUpdate("UPDATE `bank`.`creditcard` SET `invoiceAmount` = '"
                    + bankAccount.getCreditCard().getInvoiceAmount() + "' WHERE (`idCreditCard` = '"
                    + bankAccount.getCreditCard().getIdCreditCard() + "');");
        } catch (SQLException e) {
            e.printStackTrace();

        }


//        try {
//            Statement statement = connection.getConnection().createStatement();
//            statement.executeUpdate("UPDATE `bank`.`credit` SET " +
//                    "`howMuchToPay` = " + bankAccount.getCredit().getHowMuchToPay() +
//                    ", `percentRate` = " + bankAccount.getCredit().getPercentRate() +
//                    " , `paid` = " + bankAccount.getCredit().getPaid() +
//                    ", `theTotalAmountYouPay` = " + bankAccount.getCredit().getTheTotalAmountYouPay() +
//                    ", `countMonthsToPay` = " + bankAccount.getCredit().getCountMonthsToPay() +
//                    ", `bankProfit` = " + bankAccount.getCredit().getBankProfit() +
//                    ", `howMuchIsTheLoan` = " + bankAccount.getCredit().getHowMuchIsTheLoan() +
//                    ", `paymentPerMonth` = " + bankAccount.getCredit().getPaymentPerMonth() +
//                    "WHERE `idCredit`=" + bankAccount.getCredit().getIdCredit() + ";");
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }


    }
}
