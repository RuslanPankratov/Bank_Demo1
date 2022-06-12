package bank.core.service.credit.dto.creditCard.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCreditCardResponse {

    private String login;
    private String password;
    private BigDecimal invoiceAmount;
    private BigDecimal withdrawalLimit;
    private int idCreditCard;
    private Integer idUser;
}
