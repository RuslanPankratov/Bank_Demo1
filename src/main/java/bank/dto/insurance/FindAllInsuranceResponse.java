package bank.dto.insurance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllInsuranceResponse {

    @NotNull
    private List<InsuranceDTO> insuranceDTOS;

}
