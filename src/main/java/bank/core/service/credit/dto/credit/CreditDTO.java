package bank.core.service.credit.dto.credit;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CreditDTO {

    @Range(min = 1)
    @NotNull
    private Integer idCredit;
    @Range(min = 0)
    @NotNull
    private BigDecimal howMuchToPay; //надо выплатить ещё
    @Range(min = 0)
    @NotNull
    private BigDecimal percentRate; // процентная ставка
    @Range(min = 0)
    @NotNull
    private BigDecimal paid;//выплачено
    @Range(min = 0)
    @NotNull
    private BigDecimal theTotalAmountYouPay;//сколько в общем надо выплатить
    @Range(min = 0)
    @NotNull
    private BigDecimal countMonthsToPay; //количество месяцев ещё платить
    @Range(min = 0)
    @NotNull
    private BigDecimal bankProfit; //прибыль банка
    @Range(min = 0)
    @NotNull
    private BigDecimal howMuchIsTheLoan;// сколько берёт кредита человек
    @Range(min = 0)
    @NotNull
    private BigDecimal paymentPerMonth;//сколько в месяц платить
    @Range(min = 1)
    @NotNull
    private Integer idUser;


}
