package bank.dto.insurance.find;

import bank.dto.insurance.InsuranceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetByIdInsuranceResponse {

    private InsuranceDTO insurance;
}
