package bank.demo.dto.dto;

import bank.demo.dto.enum_class.TransactionType;

public class TransactionDTO {

    private double amount;
    private TransactionType transactionType;

    public TransactionDTO(double amount, TransactionType transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
