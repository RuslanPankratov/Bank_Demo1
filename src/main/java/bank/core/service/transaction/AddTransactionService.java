package bank.core.service.transaction;

import bank.domain.TransactionEntity;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
import bank.core.service.credit.dto.transaction.add.AddTransactionResponse;
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

    public AddTransactionResponse save(AddTransactionRequest request) {

        TransactionEntity transactionEntity = convert(request);
        transactionRepository.save(transactionEntity);

        return convertResponse(request);
    }

    private TransactionEntity convert(AddTransactionRequest request) {
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmount(request.getAmount());
        transaction.setTransactionType(request.getTransactionType());
        transaction.setIdUser(request.getIdUser());
        transaction.setTransactionSuccess(request.getTransactionSuccess());
        transaction.setWithWhomTheDeal(request.getWithWhomTheDeal());
        transaction.setDate(new Date());

        log.debug("Received request: {}", request);
        log.debug("Return TransactionEntity: {}", transaction);

        return transaction;
    }


    private AddTransactionResponse convertResponse(AddTransactionRequest request) {
        AddTransactionResponse addTransactionResponse = new AddTransactionResponse();
        addTransactionResponse.setAmount(request.getAmount());
        addTransactionResponse.setTransactionType(request.getTransactionType());
        addTransactionResponse.setIdUser(request.getIdUser());
        addTransactionResponse.setTransactionSuccess(request.getTransactionSuccess());
        addTransactionResponse.setWithWhomTheDeal(request.getWithWhomTheDeal());

        log.debug("Received request: {}", request);
        log.debug("Return TransactionEntity: {}", addTransactionResponse);

        return addTransactionResponse;
    }
}
