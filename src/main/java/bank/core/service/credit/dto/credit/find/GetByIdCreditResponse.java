package bank.core.service.credit.dto.credit.find;

import bank.core.service.credit.dto.credit.CreditDTO;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GetByIdCreditResponse {

    private CreditDTO creditDTO;
}
