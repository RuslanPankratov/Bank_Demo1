package bank.core.service.creditCard;


import bank.domain.CreditCardEntity;
import bank.dto.creditCard.CreditCardDTO;
import bank.dto.creditCard.find.FindAllCreditCardResponse;
import bank.repository.CreditCardRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class FindAllCreditCardService {
    private final CreditCardRepository repository;


    public FindAllCreditCardResponse findAll(){

        List<CreditCardDTO> dtos = repository.findAll().stream()
                .map(this::convert)
                .toList();

        FindAllCreditCardResponse findAllCreditCardResponse = new FindAllCreditCardResponse(dtos);

        log.debug("Find All Credit Card: {}", dtos);
        log.debug("Find All Credit Card: {}", findAllCreditCardResponse);

        return findAllCreditCardResponse;
    }


    private CreditCardDTO convert(CreditCardEntity entity){
        return new CreditCardDTO(entity.getLogin(), entity.getPassword(),
                entity.getInvoiceAmount(),entity.getWithdrawalLimit(),entity.getIdCreditCard(),entity.getIdUser());
    }
}
