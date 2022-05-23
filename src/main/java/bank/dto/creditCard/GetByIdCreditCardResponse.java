package bank.dto.creditCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCreditCardResponse {

    @NotNull
    private CreditCardDTO creditCardDTO;
}
