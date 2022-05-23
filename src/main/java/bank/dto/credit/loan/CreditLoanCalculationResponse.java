package bank.dto.credit.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditLoanCalculationResponse {

    @Range(min = 0)
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
    @Range(min = 0)
    private Integer idUser;
}
