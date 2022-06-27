package bank.dto.insurance.calculator;

import bank.enum_class.InsuranceType;
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
    private BigDecimal sum;
    @NotNull
    @NotNull
    private InsuranceType typeInsurance;

}
