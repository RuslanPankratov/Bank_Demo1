package bank.dto.insurance.calculator;

import bank.enum_class.TypeInsurance;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class InsuranceCalculatorRequest {
    @Range(min = 1)
    private Integer idUser;
    @Range(min = 1)
    private BigDecimal sum;
    @NotNull
    private TypeInsurance typeInsurance;
}
