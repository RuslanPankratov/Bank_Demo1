package bank.core.service.transaction;

import bank.domain.TransactionEntity;
import bank.dto.transaction.AddTransactionRequest;
import bank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@AllArgsConstructor
public class AddTransactionService {

    private final TransactionRepository transactionRepository;

   public void transaction(AddTransactionRequest request){
       TransactionEntity transactionEntity = convert(request);
       transactionRepository.save(transactionEntity);

    }

   private TransactionEntity convert(AddTransactionRequest request){
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setIdUser(request.getIdUser());
        transaction.setTransactionSuccess(request.getTransactionSuccess());
        transaction.setBetweenWhomTheTransaction(request.getBetweenWhomTheTransaction());
        transaction.setDate(new Date());

        log.debug("Received request: {}", request);
        log.debug("Return TransactionEntity: {}", transaction);

        return transaction;
    }
}
