package bank.dto.transaction;

import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTransactionRequest {

    @Range(min = 1)
    private BigDecimal amount;
    @NotNull
    private TransactionType transactionType;
    @NotNull
    private BetweenWhomTheTransaction betweenWhomTheTransaction;
    @NotNull
    private TransactionSuccess transactionSuccess;
    @Range(min = 1)
    private Integer idUser;
}
