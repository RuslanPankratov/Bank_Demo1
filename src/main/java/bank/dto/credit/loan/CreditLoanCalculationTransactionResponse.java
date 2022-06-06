package bank.dto.credit.loan;

import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditLoanCalculationTransactionResponse {

    private BigDecimal amount;
    private TransactionType transactionType;
    private BetweenWhomTheTransaction betweenWhomTheTransaction;
    private TransactionSuccess transactionSuccess;
    private Integer idUser;
}
