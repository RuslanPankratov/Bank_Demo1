package bank.dto.transaction.add;

import bank.enum_class.WithWhomTheDeal;
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
    @NotNull
    private BigDecimal amount;
    @NotNull
    private TransactionType transactionType;
    @NotNull
    private WithWhomTheDeal withWhomTheDeal;
    @NotNull
    private TransactionSuccess transactionSuccess;
    private Integer idUser;

}
