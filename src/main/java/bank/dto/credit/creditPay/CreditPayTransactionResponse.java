package bank.dto.credit.creditPay;

import bank.enum_class.BetweenWhomTheTransaction;
import bank.enum_class.TransactionSuccess;
import bank.enum_class.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreditPayTransactionResponse {

    private BigDecimal amount;
    private TransactionType transactionType;
    private BetweenWhomTheTransaction betweenWhomTheTransaction;
    private TransactionSuccess transactionSuccess;
    private Integer idUser;

}
