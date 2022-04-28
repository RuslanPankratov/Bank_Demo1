package bank.demo.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "credits")
//для того, чтобы потом использовать в коде это будет как ссылка на этот класс, когда будем работать с бд
@Table(name = "credit")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credit")
    private int idCredit;
    @Column(name = "how_much_to_pay")
    private BigDecimal howMuchToPay; //надо выплатить ещё
    @Column(name = "percent_rate")
    private BigDecimal percentRate; // процентная ставка
    @Column(name = "paid")
    private BigDecimal paid;//выплачено
    @Column(name = "the_total_amount_you_pay")
    private BigDecimal theTotalAmountYouPay;//сколько в общем надо выплатить
    @Column(name = "count_months_to_pay")
    private BigDecimal countMonthsToPay; //количество месяцев ещё платить
    @Column(name = "bank_profit")
    private BigDecimal bankProfit; //прибыль банка
    @Column(name = "how_much_is_the_loan")
    private BigDecimal howMuchIsTheLoan;// сколько берёт кредита человек
    @Column(name = "payment_per_month")
    private BigDecimal paymentPerMonth;//сколько в месяц платить  нельзя создавая такие колонки, они должны быть ещё созданы и в бд
//    @Column(name = "id_bank_account")
//    private int idBankAccount;
}
