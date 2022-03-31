package bank.demo.dto.services;

import bank.demo.dto.dto.BankAccount;
import org.springframework.stereotype.Component;
@Component
public class LoanPaymentCalculator {


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

    }
}
