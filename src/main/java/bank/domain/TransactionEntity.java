package bank.domain;

import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;


@Entity(name = "transaction")
@Table(name = "transaction")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "amount")
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;
    @Enumerated(EnumType.STRING)
    @Column(name = "between_whom_the_transaction")
    private BetweenWhomTheTransaction betweenWhomTheTransaction;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_success")
    private TransactionSuccess transactionSuccess;
    @Column(name = "id_user")
    private Integer idUser;
    @DateTimeFormat
    @Column(name = "date")
    private Date date;

}
