package bank.core.service.credit.dto.credit.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCreditResponse {

    private Integer idCredit;
    private BigDecimal howMuchToPay;
    private BigDecimal percentRate;
    private BigDecimal paid;
    private BigDecimal theTotalAmountYouPay;
    private BigDecimal countMonthsToPay;
    private BigDecimal bankProfit;
    private BigDecimal howMuchIsTheLoan;
    private BigDecimal paymentPerMonth;
    private Integer idUser;
}
