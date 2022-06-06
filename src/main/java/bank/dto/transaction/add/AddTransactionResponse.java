package bank.dto.transaction.add;

import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddTransactionResponse {

    private BigDecimal amount;
    private TransactionType transactionType;
    private BetweenWhomTheTransaction betweenWhomTheTransaction;
    private TransactionSuccess transactionSuccess;
    private Integer idUser;

}
