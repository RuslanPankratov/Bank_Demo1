package bank.dto.credit;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Data
public class CreditDTO {

    @Range(min = 1)
    private Integer idCredit;
    @Range(min = 0)
    private BigDecimal howMuchToPay; //надо выплатить ещё
    @Range(min = 0)
    private BigDecimal percentRate; // процентная ставка
    @Range(min = 0)
    private BigDecimal paid;//выплачено
    @Range(min = 0)
    private BigDecimal theTotalAmountYouPay;//сколько в общем надо выплатить
    @Range(min = 0)
    private BigDecimal countMonthsToPay; //количество месяцев ещё платить
    @Range(min = 0)
    private BigDecimal bankProfit; //прибыль банка
    @Range(min = 0)
    private BigDecimal howMuchIsTheLoan;// сколько берёт кредита человек
    @Range(min = 0)
    private BigDecimal paymentPerMonth;//сколько в месяц платить
    @Range(min = 1)
    private Integer idUser;


}
