package bank.dto.credit.creditPay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditPayResponse {

    @Range(min = 1)
    private Integer idCredit;
    @Range(min = 0)
    private BigDecimal howMuchToPay;
    @Range(min = 0)
    private BigDecimal percentRate;
    @Range(min = 0)
    private BigDecimal paid;
    @Range(min = 0)
    private BigDecimal theTotalAmountYouPay;
    @Range(min = 0)
    private BigDecimal countMonthsToPay;
    @Range(min = 0)
    private BigDecimal bankProfit; //прибыль банка
    @Range(min = 0)
    private BigDecimal howMuchIsTheLoan;// сколько берёт кредита человек
    @Range(min = 0)
    private BigDecimal paymentPerMonth;//сколько в месяц платить
    @Range(min = 1)
    private Integer idUser;
}
