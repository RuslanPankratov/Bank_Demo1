package bank.core.service.creditCard;



import bank.domain.CreditCardEntity;
import bank.dto.creditCard.add.AddCreditCardRequest;
import bank.dto.creditCard.add.AddCreditCardResponse;
import bank.repository.CreditCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddCreditCardService {


    @Autowired
    private CreditCardRepository creditCardRepository;


    public AddCreditCardResponse add(AddCreditCardRequest request){

        var entity = convert(request);
        entity.setIdCreditCard(request.getIdCreditCard());
        var createdEntity = creditCardRepository.save(entity);
        var response = new AddCreditCardResponse();
        response.setCreatedCreditCardId(createdEntity.getIdCreditCard());

        log.debug("Credit Card Entity request: {}", entity);
        log.debug("Successfully saved: {}", createdEntity);
        log.debug("Sending response: {}", response);

        return response;
    }


    private CreditCardEntity convert(AddCreditCardRequest request){
        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setLogin(request.getLogin());
        creditCard.setPassword(request.getPassword());
        creditCard.setInvoiceAmount(request.getInvoiceAmount());
        creditCard.setWithdrawalLimit(request.getWithdrawalLimit());

        return creditCard;
    }


}
