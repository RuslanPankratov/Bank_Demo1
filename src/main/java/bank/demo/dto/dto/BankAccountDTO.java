package bank.demo.dto.dto;
import java.util.List;

public class BankAccountDTO {
    private UserDTO user;
    private CreditCardDTO creditCard;
    private float clientId;
    private List<TransactionDTO> transactions; //транзакции должны быть по ид на каждого человека, с возможностью иметь много транзакций
    //то есть у транзации должен быть свой ид, но и в тоже время этот ид должен быть привзяаным к человеку
    //разобраться позже в этом
    private CreditDTO credit;
    private InsuranceDTO insurance;

    public BankAccountDTO(UserDTO user, CreditCardDTO creditCard) {
        this.user = user;
        this.creditCard = creditCard;
    }


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CreditCardDTO getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDTO creditCard) {
        this.creditCard = creditCard;
    }

    public float getClientId() {
        return clientId;
    }

    public void setClientId(float clientId) {
        this.clientId = clientId;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    public CreditDTO getCredit() {
        return credit;
    }

    public void setCredit(CreditDTO credit) {
        this.credit = credit;
    }

    public InsuranceDTO getInsurance() {
        return insurance;
    }

    public void setInsurance(InsuranceDTO insurance) {
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
