package bank.core.service.credit.dto.creditCard;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreditCardToOtherRequest {

    @Range(min = 1)
    @NotNull
    private BigDecimal departureAmount;

}
