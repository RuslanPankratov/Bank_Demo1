package bank.demo.dto.dto;

import org.springframework.stereotype.Component;

import java.util.List;

public class BankAccount {
    private User user;
   // private Transaction transaction;
    private CreditCard creditCard;
    private float clientId;
    private List<Transaction> transactions; //транзакции должны быть по ид на каждого человека, с возможностью иметь много транзакций
    //то есть у транзации должен быть свой ид, но и в тоже время этот ид должен быть привзяаным к человеку
    //разобраться позже в этом
    private Credit credit;
    private Insurance insurance;

    public BankAccount(User user, CreditCard creditCard) {
        this.user = user;
        this.creditCard = creditCard;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Transaction getTransaction() {
//        return transaction;
//    }
//
//    public void setTransaction(Transaction transaction) {
//        this.transaction = transaction;
//    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public float getClientId() {
        return clientId;
    }

    public void setClientId(float clientId) {
        this.clientId = clientId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }


    @Override
    public String toString() {
        return "BankAccount{" +
                "user=" + user +
                ", creditCard=" + creditCard +
                ", clientId=" + clientId +
                ", transactions=" + transactions +
                ", credit=" + credit +
                ", insurance=" + insurance +
                '}';
    }
}
