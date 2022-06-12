package bank.core.service.credit.dto.creditCard.find;

import bank.core.service.credit.dto.creditCard.CreditCardDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindAllCreditCardResponse {

    private List<CreditCardDTO> creditCardDTOS;
}
