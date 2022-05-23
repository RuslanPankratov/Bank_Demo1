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
//    @Column(name = "blocked",columnDefinition = "TINYINT")
//    private boolean blocked;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "invoice_amount")
    private BigDecimal invoiceAmount; //сума не счету
    @Column(name = "withdrawal_limit")
    private BigDecimal withdrawalLimit;//ограничение на снятие суммы денег за день,
    @Column(name = "id_user")
    private Integer idUser;

    //@OneToMany(cascade = CascadeType.ALL)
    //    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    @Column(name = "id_bank_account")
//    private int idBankAccount;

}