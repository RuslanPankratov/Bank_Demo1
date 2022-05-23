package bank.dto.insurance.add;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@Data
public class AddInsuranceResponse {

    @Range(min = 1)
    private Integer createdInsuranceId;

}
