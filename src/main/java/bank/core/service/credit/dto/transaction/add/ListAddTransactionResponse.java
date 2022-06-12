package bank.core.service.credit.dto.transaction.add;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor

public class ListAddTransactionResponse {

    private List<AddTransactionResponse> responses;

}
