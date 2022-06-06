package bank.dto.insurance.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInsuranceRequest {

    @Range(min = 0)
    @NotNull
    private BigDecimal sumInsured;
    @Range(min = 0)
    @NotNull
    private BigDecimal insurancePaid;
    @Range(min = 1)
    @NotNull
    private Integer idInsurance;
    @Range(min = 1)
    @NotNull
    private Integer idUser;

}
