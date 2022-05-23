package bank.dto.creditCard.add;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddCreditCardRequest {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @Range(min = 0)
    private BigDecimal invoiceAmount;
    @Range(min = 0)
    private BigDecimal withdrawalLimit;
    @Range(min = 1)
    private Integer idCreditCard;
    @Range(min = 1)
    private Integer idUser;
}
