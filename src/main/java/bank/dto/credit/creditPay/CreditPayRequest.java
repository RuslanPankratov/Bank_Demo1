package bank.dto.credit.creditPay;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class CreditPayRequest {

    @Range(min = 1)
    private Integer idUser;
}
