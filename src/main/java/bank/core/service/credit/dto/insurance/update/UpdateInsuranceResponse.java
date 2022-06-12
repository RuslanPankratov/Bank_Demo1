package bank.core.service.credit.dto.insurance.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInsuranceResponse {

    private BigDecimal sumInsured;
    private BigDecimal insurancePaid;
    private Integer idInsurance;
    private Integer idUser;

}
