package bank.core.service.credit.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
@AllArgsConstructor

public class InsuranceDTO {

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
