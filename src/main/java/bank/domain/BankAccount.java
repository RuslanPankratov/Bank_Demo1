package bank.domain;

import lombok.Data;


@Data
public class BankAccount {
    private UserEntity user;
    private CreditCardEntity creditCard;
    private CreditEntity credit;
    private InsuranceEntity insurance;
    private int id;
}
