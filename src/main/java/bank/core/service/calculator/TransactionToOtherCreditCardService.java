package bank.core.service.calculator;

import bank.core.calculator.TransactionToOtherCreditCard;
import bank.core.service.transaction.AddTransactionService;
import bank.domain.CreditCardEntity;
import bank.core.service.credit.dto.creditCard.CreditCardToOtherRequest;
import bank.core.service.credit.dto.transaction.add.AddTransactionRequest;
import bank.core.service.credit.dto.transaction.add.AddTransactionResponse;
import bank.core.service.credit.dto.transaction.add.ListAddTransactionResponse;
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

    public Optional<ListAddTransactionResponse> transaction(CreditCardToOtherRequest request, Integer idSender
            , Integer idRecipient) {

        log.debug("Received Credit Card To Other request: {}", request);

        Optional<CreditCardEntity> creditCardSender = creditCardRepository.findByIdUser(idSender);
        Optional<CreditCardEntity> creditCardRecipient = creditCardRepository.findByIdUser(idRecipient);
        List<AddTransactionResponse> addTransactionResponses = new ArrayList<>();
        ListAddTransactionResponse listAddTransactionResponse = new ListAddTransactionResponse(addTransactionResponses);
        Optional<ListAddTransactionResponse> transactionsOptional = Optional.of(listAddTransactionResponse);

        log.debug("Received Credit Card Sender request: {}", creditCardSender);
        log.debug("Received Credit Card Recipient request: {}", creditCardRecipient);
        log.debug("Optional List Add Transaction Response: {}", transactionsOptional);

        if (creditCardSender.isPresent() && creditCardRecipient.isPresent()) {
            transactionsOptional = convertResponse(creditCardSender, creditCardRecipient, request);
        }
        return transactionsOptional;
    }

    private Optional<ListAddTransactionResponse> convertList(List<AddTransactionRequest> request) {
        List<AddTransactionResponse> addTransactionResponses = new ArrayList<>();

        addTransactionResponses.add(convert(request.get(0)));
        addTransactionResponses.add(convert(request.get(1)));

        return Optional.of(new ListAddTransactionResponse(addTransactionResponses));
    }

    private AddTransactionResponse convert(AddTransactionRequest request) {
        return new AddTransactionResponse(request.getAmount(),
                request.getTransactionType(), request.getWithWhomTheDeal(), request.getTransactionSuccess(),
                request.getIdUser());
    }


    private Optional<ListAddTransactionResponse> convertResponse(
            Optional<CreditCardEntity> creditCardSender, Optional<CreditCardEntity> creditCardRecipient
            , CreditCardToOtherRequest request) {

        CreditCardEntity creditCardSenderEntity = creditCardSender.get();
        CreditCardEntity creditCardRecipientEntity = creditCardRecipient.get();

        List<AddTransactionRequest> addTransactionRequest =
                transactionToOtherCreditCard.transaction(creditCardSenderEntity, creditCardRecipientEntity
                        , request.getDepartureAmount());

        creditCardRepository.save(creditCardSenderEntity);
        creditCardRepository.save(creditCardRecipientEntity);
        addTransactionService.save(addTransactionRequest.get(0));
        addTransactionService.save(addTransactionRequest.get(1));

        Optional<ListAddTransactionResponse> transactionsOptional = convertList(addTransactionRequest);

        log.debug("Changed Credit Card Sender Entity request: {}", creditCardSenderEntity);
        log.debug("Changed Credit Card Recipient Entity request: {}", creditCardRecipientEntity);
        log.debug("Changed Add Transaction Request request: {}", addTransactionRequest);
        log.debug("Changed Add Transaction Response request: {}", transactionsOptional);

        return transactionsOptional;
    }
}
