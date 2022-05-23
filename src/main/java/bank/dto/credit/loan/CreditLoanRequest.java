package bank.dto.credit.loan;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
public class CreditLoanRequest {

    @Range(min = 1)
    private Integer idUser;
    @Range(min = 0)
    private BigDecimal currentPercentUser;
    @Range(min = 0)
    private BigDecimal amountOfCredit;
    @Range(min = 0)
    private BigDecimal numberOfMonthsOfLoan;

}
