package bank.dto.creditCard;


import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
public class CreditCardToOtherRequest {

    @Range(min = 1)
    private Integer senderUserId;
    @Range(min = 1)
    private Integer recipientUserId;
    @Range(min = 1)
    private BigDecimal departureAmount;

}
