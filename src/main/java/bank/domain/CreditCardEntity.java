package bank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "credit_cards")
@Table(name = "credit_card")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit_card")
    private Integer idCreditCard;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "invoice_amount")
    private BigDecimal invoiceAmount;
    @Column(name = "withdrawal_limit")
    private BigDecimal withdrawalLimit;
    @Column(name = "id_user")
    private Integer idUser;

}
