package bank.core.service.creditCard;

import bank.domain.CreditCardEntity;
import bank.dto.creditCard.GetByIdCreditCardResponse;
import bank.dto.creditCard.UpdateCreditCardRequest;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@AllArgsConstructor
@Component
public class UpdateCreditCardService {

    private final CreditCardRepository repository;

    @Transactional
    public void update(UpdateCreditCardRequest request){
        repository.findById(request.getIdCreditCard())
                .map(creditCard -> updateFields(creditCard, request))
                .ifPresent(repository::save);
    }


    private CreditCardEntity updateFields(CreditCardEntity entity, UpdateCreditCardRequest request){
        entity.setLogin(request.getLogin());
        entity.setPassword(request.getPassword());
        entity.setWithdrawalLimit(request.getWithdrawalLimit());
        entity.setInvoiceAmount(request.getInvoiceAmount());

        log.debug("Update Credit Card Entity: {}", entity);

        return entity;
    }
}
