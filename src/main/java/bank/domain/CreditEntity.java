package bank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "credits")
@Table(name = "credit")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor
public class CreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit")
    private int idCredit;
    @Column(name = "how_much_to_pay")
    private BigDecimal howMuchToPay;
    @Column(name = "percent_rate")
    private BigDecimal percentRate;
    @Column(name = "paid")
    private BigDecimal paid;
    @Column(name = "the_total_amount_you_pay")
    private BigDecimal theTotalAmountYouPay;
    @Column(name = "count_months_to_pay")
    private BigDecimal countMonthsToPay;
    @Column(name = "bank_profit")
    private BigDecimal bankProfit;
    @Column(name = "how_much_is_the_loan")
    private BigDecimal howMuchIsTheLoan;
    @Column(name = "payment_per_month")
    private BigDecimal paymentPerMonth;
    @Column(name = "id_user")
    private Integer idUser;

}
