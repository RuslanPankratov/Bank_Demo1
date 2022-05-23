package bank.dto.creditCard;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class UpdateCreditCardRequest {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @Range(min = 0)
    private BigDecimal invoiceAmount;
    @Range(min = 0)
    private BigDecimal withdrawalLimit;
    @Range(min = 1)
    private int idCreditCard;
    @Range(min = 1)
    private Integer idUser;
}
