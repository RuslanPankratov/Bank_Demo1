package bank.demo.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

//@Entity(name = "Credit")
////для того, чтобы потом использовать в коде это будет как ссылка на этот класс, когда будем работать с бд
//@Table(name = "credit")
//@Data
//@AllArgsConstructor
//@Transactional
//@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCredit")
    private int idCredit;
    @Column(name = "howMuchToPay")
    private BigDecimal howMuchToPay; //надо выплатить ещё
    @Column(name = "percentRate")
    private BigDecimal percentRate; // процентная ставка
    @Column(name = "paid")
    private BigDecimal paid;//выплачено
    @Column(name = "theTotalAmountYouPay")
    private BigDecimal theTotalAmountYouPay;//сколько в общем надо выплатить
    @Column(name = "countMonthsToPay")
    private BigDecimal countMonthsToPay; //количество месяцев ещё платить
    @Column(name = "bankProfit")
    private BigDecimal bankProfit; //прибыль банка
    @Column(name = "howMuchIsTheLoan")
    private BigDecimal howMuchIsTheLoan;// сколько берёт кредита человек
    @Column(name = "paymentPerMonth")
    private BigDecimal paymentPerMonth;//сколько в месяц платить  нельзя создавая такие колонки, они должны быть ещё созданы и в бд
}
