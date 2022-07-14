package bank.core.service.calculator;

import bank.core.calculator.TransactionToOtherCreditCard;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.dto.creditCard.CreditCardToOtherRequest;
import bank.dto.transaction.add.AddTransactionRequest;
import bank.dto.transaction.add.AddTransactionResponse;
import bank.dto.transaction.add.ListAddTransactionResponse;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class TransactionToOtherCreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final AddTransactionService addTransactionService;
    private final TransactionToOtherCreditCard transactionToOtherCreditCard;

    public ListAddTransactionResponse transaction(CreditCardToOtherRequest request, Integer idSender
            , Integer idRecipient) {

        log.debug("Received Credit Card To Other request: {}", request);

        Optional<CreditCardEntity> creditCardSender = creditCardRepository.findById(idSender);
        Optional<CreditCardEntity> creditCardRecipient = creditCardRepository.findById(idRecipient);
        List<AddTransactionResponse> addTransactionResponses = new ArrayList<>();
        ListAddTransactionResponse listAddTransactionResponse = new ListAddTransactionResponse(addTransactionResponses);

        log.debug("Received Credit Card Sender request: {}", creditCardSender);
        log.debug("Received Credit Card Recipient request: {}", creditCardRecipient);
        log.debug("Optional List Add Transaction Response: {}", listAddTransactionResponse);

        if (creditCardSender.isPresent() && creditCardRecipient.isPresent()) {
            listAddTransactionResponse = convertResponse(creditCardSender.get(), creditCardRecipient.get(), request);
        }
        return listAddTransactionResponse;
    }

    private ListAddTransactionResponse convertList(List<AddTransactionRequest> request) {
        List<AddTransactionResponse> addTransactionResponses = new ArrayList<>();

        addTransactionResponses.add(convert(request.get(0)));
        addTransactionResponses.add(convert(request.get(1)));

        return new ListAddTransactionResponse(addTransactionResponses);
    }

    private AddTransactionResponse convert(AddTransactionRequest request) {
        return new AddTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getWithWhomTheDeal(), request.getTransactionSuccess(),
                request.getIdUser());
    }


    private ListAddTransactionResponse convertResponse(
            CreditCardEntity creditCardSender, CreditCardEntity creditCardRecipient
            , CreditCardToOtherRequest request) {

        List<AddTransactionRequest> addTransactionRequest =
                transactionToOtherCreditCard.transaction(creditCardSender, creditCardRecipient
                        , request.getDepartureAmount());

        creditCardRepository.save(creditCardSender);
        creditCardRepository.save(creditCardRecipient);
        addTransactionService.save(addTransactionRequest.get(0));
        addTransactionService.save(addTransactionRequest.get(1));

        ListAddTransactionResponse transactionsOptional = convertList(addTransactionRequest);

        log.debug("Changed Credit Card Sender Entity request: {}", creditCardSender);
        log.debug("Changed Credit Card Recipient Entity request: {}", creditCardRecipient);
        log.debug("Changed Add Transaction Request request: {}", addTransactionRequest);
        log.debug("Changed Add Transaction Response request: {}", transactionsOptional);

        return transactionsOptional;
    }
}
