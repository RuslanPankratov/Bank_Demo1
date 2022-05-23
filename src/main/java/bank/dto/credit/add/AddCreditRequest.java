package bank.dto.credit.add;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddCreditRequest {


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
    private BigDecimal bankProfit;
    @Range(min = 0)
    private BigDecimal howMuchIsTheLoan;
    @Range(min = 0)
    private BigDecimal paymentPerMonth;
    @Range(min = 1)
    private Integer idUser;
}
