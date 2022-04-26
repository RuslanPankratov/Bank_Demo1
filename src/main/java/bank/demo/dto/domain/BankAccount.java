package bank.demo.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class BankAccount {
    private User user;
    private CreditCard creditCard;
    private Credit credit;
    private Insurance insurance;
    private int id;
}
