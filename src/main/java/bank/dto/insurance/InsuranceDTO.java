package bank.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {

    @Range(min = 0)
    private BigDecimal sumInsured;
    @Range(min = 0)
    private BigDecimal insurancePaid;
    @Range(min = 1)
    private int idInsurance;
    @Range(min = 1)
    private Integer idUser;
}
