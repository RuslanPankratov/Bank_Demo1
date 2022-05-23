package bank.dto.insurance.pay;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsurancePayResponse {

    @Range(min = 1)
    private BigDecimal sumInsured;
    @Range(min = 0)
    private BigDecimal insurancePaid;
    @Range(min = 1)
    private Integer idInsurance;
    @Range(min = 1)
    private Integer idUser;
}
