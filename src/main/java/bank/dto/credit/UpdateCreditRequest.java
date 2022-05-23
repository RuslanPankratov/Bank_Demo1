package bank.dto.credit;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
public class UpdateCreditRequest {


    @Range(min = 1)
    private int idCredit;
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
