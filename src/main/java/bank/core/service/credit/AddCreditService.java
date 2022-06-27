package bank.core.service.credit;

import bank.domain.CreditEntity;
import bank.dto.credit.add.AddCreditRequest;
import bank.dto.credit.add.AddCreditResponse;
import bank.repository.CreditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddCreditService {

    private CreditRepository repository;


    public AddCreditResponse add(AddCreditRequest request) {

        CreditEntity entity = convert(request);

        CreditEntity createdEntity = repository.save(entity);
        AddCreditResponse response = new AddCreditResponse();
        response.setCreatedCreditId(createdEntity.getIdCredit());

        log.debug("Credit Entity request: {}", entity);
        log.debug("Successfully saved: {}", createdEntity);
        log.debug("Sending response: {}", response);

        return response;
    }

    private CreditEntity convert(AddCreditRequest request) {
        return new CreditEntity(request.getIdCredit(), request.getHowMuchToPay()
                , request.getPercentRate(), request.getPaid(), request.getTheTotalAmountYouPay()
                , request.getCountMonthsToPay(), request.getBankProfit(), request.getHowMuchIsTheLoan()
                , request.getPaymentPerMonth(), request.getIdUser());
    }

}
