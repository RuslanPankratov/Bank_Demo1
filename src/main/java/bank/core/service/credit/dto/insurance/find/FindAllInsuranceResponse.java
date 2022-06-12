package bank.core.service.credit.dto.insurance.find;

import bank.core.service.credit.dto.insurance.InsuranceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindAllInsuranceResponse {

    private List<InsuranceDTO> insurances;

}
