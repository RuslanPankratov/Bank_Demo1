package bank.dto.credit.find;

import bank.dto.credit.CreditDTO;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GetByIdCreditResponse {

    private CreditDTO creditDTO;
}
