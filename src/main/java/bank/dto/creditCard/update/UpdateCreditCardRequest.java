package bank.dto.creditCard.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCreditCardRequest {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @Range(min = 0)
    @NotNull
    private BigDecimal invoiceAmount;
    @Range(min = 0)
    @NotNull
    private BigDecimal withdrawalLimit;
    @Range(min = 1)
    @NotNull
    private Integer idCreditCard;
    @Range(min = 1)
    @NotNull
    private Integer idUser;
}
