package bank.dto.insurance.find;

import bank.dto.insurance.InsuranceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindAllInsuranceResponse {

    private List<InsuranceDTO> insurances;

}
