package bank.dto.credit.find;

import bank.dto.credit.CreditDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FindAllCreditResponse {

    private List<CreditDTO> creditDTOS;

}
