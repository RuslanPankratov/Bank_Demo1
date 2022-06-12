package bank.core.service.credit.dto.credit.update;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class UpdateCreditRequest {


    @Range(min = 1)
    @NotNull
    private int idCredit;
    @Range(min = 0)
    @NotNull
    private BigDecimal howMuchToPay;
    @Range(min = 0)
    @NotNull
    private BigDecimal percentRate;
    @Range(min = 0)
    @NotNull
    private BigDecimal paid;
    @Range(min = 0)
    @NotNull
    private BigDecimal theTotalAmountYouPay;
    @Range(min = 0)
    @NotNull
    private BigDecimal countMonthsToPay;
    @Range(min = 0)
    @NotNull
    private BigDecimal bankProfit;
    @Range(min = 0)
    @NotNull
    private BigDecimal howMuchIsTheLoan;
    @Range(min = 0)
    @NotNull
    private BigDecimal paymentPerMonth;
    @Range(min = 1)
    @NotNull
    private Integer idUser;
}
