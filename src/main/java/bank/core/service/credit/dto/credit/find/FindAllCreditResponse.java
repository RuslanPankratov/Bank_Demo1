package bank.core.service.credit.dto.credit.find;

import bank.core.service.credit.dto.credit.CreditDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindAllCreditResponse {

    private List<CreditDTO> creditDTOS;

}
