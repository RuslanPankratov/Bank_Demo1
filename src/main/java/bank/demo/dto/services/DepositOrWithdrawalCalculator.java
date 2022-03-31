package bank.demo.dto.services;

import bank.demo.dto.enum_class.TransactionType;
import bank.demo.dto.dto.BankAccount;
import bank.demo.dto.dto.Transaction;
import org.springframework.stereotype.Component;
@Component
public class DepositOrWithdrawalCalculator {




    public void calculator(BankAccount bankAccount, Transaction transaction){

        if(transaction.getTransactionType().equals(TransactionType.DEPOSIT)){
            calculatorDeposit(bankAccount,transaction.getAmount());
        } else if (transaction.getTransactionType().equals(TransactionType.WITHDRAWAL)){
            calculatorWithdrawal(bankAccount,transaction.getAmount());
        }

    }

    void calculatorDeposit(BankAccount bankAccount, double amountOfMoney){
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() + amountOfMoney);
    }


    void calculatorWithdrawal(BankAccount bankAccount, double amountOfMoney){
        bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount() - amountOfMoney);

    }
}
