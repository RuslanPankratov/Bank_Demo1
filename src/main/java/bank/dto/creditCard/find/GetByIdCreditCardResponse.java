package bank.dto.creditCard.find;

import bank.dto.creditCard.CreditCardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetByIdCreditCardResponse {

    private CreditCardDTO creditCardDTO;

}
