package bank.dto.creditCard;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllCreditCardResponse {

    @NotNull
    private List<CreditCardDTO> creditCardDTOS;
}
