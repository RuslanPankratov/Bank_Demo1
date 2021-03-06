package bank.domain;

import bank.enum_class.WithWhomTheDeal;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;


@Entity(name = "transaction")
@Table(name = "transaction")
@Data
@Transactional
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
    private WithWhomTheDeal withWhomTheDeal;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_success")
    private TransactionSuccess transactionSuccess;
    @Column(name = "id_user")
    private Integer idUser;
    @DateTimeFormat
    @Column(name = "date")
    private Date date;

}
