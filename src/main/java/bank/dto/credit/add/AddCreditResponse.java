package bank.dto.credit.add;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@Data
public class AddCreditResponse {

    @Range(min = 0)
    private Integer createdCreditId;
}
