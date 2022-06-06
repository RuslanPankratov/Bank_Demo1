package bank.dto.credit.creditPay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditPayRequest {

    @Range(min = 1)
    @NotNull
    private Integer idUser;
}
