package bank.core.service.creditCard;

import bank.domain.CreditCardEntity;
import bank.dto.creditCard.update.UpdateCreditCardRequest;
import bank.dto.creditCard.update.UpdateCreditCardResponse;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Component
public class UpdateCreditCardService {

    private final CreditCardRepository repository;

    @Transactional
    public UpdateCreditCardResponse update(UpdateCreditCardRequest request) {

        repository.findById(request.getIdCreditCard())
                .map(creditCard -> updateFields(creditCard, request))
                .ifPresent(repository::save);

        Optional<CreditCardEntity> creditCardEntity = repository.findById(request.getIdCreditCard());

        log.debug("Return Update Credit Card Response: {}", creditCardEntity);

        return convert(creditCardEntity);
    }


    private CreditCardEntity updateFields(CreditCardEntity entity, UpdateCreditCardRequest request) {

        entity.setLogin(request.getLogin());
        entity.setPassword(request.getPassword());
        entity.setWithdrawalLimit(request.getWithdrawalLimit());
        entity.setInvoiceAmount(request.getInvoiceAmount());
        entity.setIdUser(request.getIdUser());

        log.debug("Update Credit Card Entity: {}", entity);

        return entity;
    }


    private UpdateCreditCardResponse convert(Optional<CreditCardEntity> entityOptional) {

        UpdateCreditCardResponse response = new UpdateCreditCardResponse();

        if (entityOptional.isPresent()) {
            CreditCardEntity entity = entityOptional.get();
            response = new UpdateCreditCardResponse(entity.getLogin(), entity.getPassword(), entity.getInvoiceAmount()
                    , entity.getWithdrawalLimit(), entity.getIdCreditCard(), entity.getIdUser());
        }
        return response;
    }
}
