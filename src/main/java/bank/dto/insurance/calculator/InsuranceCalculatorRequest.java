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
public class InsuranceCalculatorRequest {

    @Range(min = 1)
    @NotNull
    private Integer idUser;
    @Range(min = 1)
    @NotNull
    private BigDecimal sum;
    @NotNull
    @NotNull
    private TypeInsurance typeInsurance;

}
