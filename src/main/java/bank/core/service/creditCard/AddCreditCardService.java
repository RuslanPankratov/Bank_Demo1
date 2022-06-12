package bank.core.service.creditCard;


import bank.domain.CreditCardEntity;
import bank.core.service.credit.dto.creditCard.add.AddCreditCardRequest;
import bank.core.service.credit.dto.creditCard.add.AddCreditCardResponse;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddCreditCardService {

    private CreditCardRepository creditCardRepository;

    public AddCreditCardResponse add(AddCreditCardRequest request) {

        CreditCardEntity entity = convert(request);

        CreditCardEntity createdEntity = creditCardRepository.save(entity);
        AddCreditCardResponse response = new AddCreditCardResponse();
        response.setCreatedCreditCardId(createdEntity.getIdCreditCard());

        log.debug("Credit Card Entity request: {}", entity);
        log.debug("Successfully saved: {}", createdEntity);
        log.debug("Sending response: {}", response);

        return response;
    }


    private CreditCardEntity convert(AddCreditCardRequest request) {
        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setLogin(request.getLogin());
        creditCard.setPassword(request.getPassword());
        creditCard.setInvoiceAmount(request.getInvoiceAmount());
        creditCard.setWithdrawalLimit(request.getWithdrawalLimit());
        creditCard.setIdUser(request.getIdUser());

        return creditCard;
    }

}
