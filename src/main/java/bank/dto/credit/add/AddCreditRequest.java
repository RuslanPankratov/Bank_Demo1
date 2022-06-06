package bank.dto.credit.add;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCreditRequest {


    @Range(min = 1)
    private Integer idCredit;
    @NotNull
    @Range(min = 0)
    private BigDecimal howMuchToPay;
    @NotNull
    @Range(min = 0)
    private BigDecimal percentRate;
    @NotNull
    @Range(min = 0)
    private BigDecimal paid;
    @NotNull
    @Range(min = 0)
    private BigDecimal theTotalAmountYouPay;
    @NotNull
    @Range(min = 0)
    private BigDecimal countMonthsToPay;
    @NotNull
    @Range(min = 0)
    private BigDecimal bankProfit;
    @NotNull
    @Range(min = 0)
    private BigDecimal howMuchIsTheLoan;
    @NotNull
    @Range(min = 0)
    private BigDecimal paymentPerMonth;
    @NotNull
    @Range(min = 1)
    private Integer idUser;
}
