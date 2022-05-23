package bank.dto.insurance.calculator;

import bank.enum_class.TypeInsurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceCalculatorResponse {
    @Range(min = 1)
    private BigDecimal sumInsured;
    @Range(min = 0)
    private BigDecimal insurancePaid;
    @Range(min = 1)
    private Integer idInsurance;
    @Range(min = 1)
    private Integer idUser;
    @NotNull
    private TypeInsurance typeInsurance;
}
