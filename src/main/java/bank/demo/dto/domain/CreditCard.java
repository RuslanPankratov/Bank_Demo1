package bank.demo.dto.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "credit_card")
@Table(name = "credit_card")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit_card")
    private int idCreditCard;
    @Column(columnDefinition = "TINYINT",name = "blocked")
    private boolean blocked;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "invoiceAmount")
    private BigDecimal invoiceAmount; //сума не счету
    @Column(name = "withdrawalLimit")
    private BigDecimal withdrawalLimit;//ограничение на снятие суммы денег за день,


}
