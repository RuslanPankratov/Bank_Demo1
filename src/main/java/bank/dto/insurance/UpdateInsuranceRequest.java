package bank.dto.insurance;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
public class UpdateInsuranceRequest {

    @Range(min = 0)
    private BigDecimal sumInsured;
    @Range(min = 0)
    private BigDecimal insurancePaid;
    @Range(min = 1)
    private int idInsurance;
    @Range(min = 1)
    private Integer idUser;
}
