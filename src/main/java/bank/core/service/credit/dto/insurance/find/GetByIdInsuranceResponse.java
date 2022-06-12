package bank.core.service.credit.dto.insurance.find;

import bank.core.service.credit.dto.insurance.InsuranceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetByIdInsuranceResponse {

    private InsuranceDTO insurance;
}
