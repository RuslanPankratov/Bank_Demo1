package bank.dto.creditCard.add;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@Data
public class AddCreditCardResponse {

    @Range(min = 1)
    private Integer createdCreditCardId;
}
