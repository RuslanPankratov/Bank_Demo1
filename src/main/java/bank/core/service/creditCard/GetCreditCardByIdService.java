package bank.core.service.creditCard;


import bank.domain.CreditCardEntity;
import bank.dto.creditCard.CreditCardDTO;
import bank.dto.creditCard.GetByIdCreditCardResponse;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class GetCreditCardByIdService {

    private final CreditCardRepository creditCardEntity;

    public GetByIdCreditCardResponse getCreditCardById(Integer id){
        GetByIdCreditCardResponse getByIdCreditCardResponse = creditCardEntity.findById(id)
                .map(this::convert)
                .map(GetByIdCreditCardResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("Credit Card with id " + id + "is not found."));

        log.debug("Return Get By Id Credit Card Response: {}", getByIdCreditCardResponse);

        return getByIdCreditCardResponse;

    }

    private CreditCardDTO convert(CreditCardEntity entity){
        return new CreditCardDTO(entity.getLogin(), entity.getPassword(),
                entity.getInvoiceAmount(),entity.getWithdrawalLimit(),entity.getIdCreditCard(),entity.getIdUser());
    }

}
