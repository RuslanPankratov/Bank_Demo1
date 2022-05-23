package bank.dto.insurance.add;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AddInsuranceRequest {

    @Range(min = 1)
    private BigDecimal sumInsured;
    @Range(min = 0)
    private BigDecimal insurancePaid;
    @Range(min = 1)
    private int idInsurance;
    @Range(min = 1)
    private Integer idUser;
}
