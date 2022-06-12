package bank.core.service.credit.dto.creditCard.find;

import bank.core.service.credit.dto.creditCard.CreditCardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetByIdCreditCardResponse {

    private CreditCardDTO creditCardDTO;

}
