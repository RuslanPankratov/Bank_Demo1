package bank.core.service.credit;


import bank.domain.CreditEntity;
import bank.core.service.credit.dto.credit.update.UpdateCreditRequest;
import bank.core.service.credit.dto.credit.update.UpdateCreditResponse;
import bank.repository.CreditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateCreditService {

    private final CreditRepository repository;

    @Transactional
    public UpdateCreditResponse update(UpdateCreditRequest request) {
        repository.findById(request.getIdCredit())
                .map(credit -> updateFields(credit, request))
                .ifPresent(repository::save);

        Optional<CreditEntity> creditEntity = repository.findById(request.getIdCredit());

        log.debug("Return Update Credit Card Response: {}", creditEntity);

        return convert(creditEntity);
    }

    private CreditEntity updateFields(CreditEntity entity, UpdateCreditRequest request) {
        entity.setHowMuchToPay(request.getHowMuchToPay());
        entity.setPercentRate(request.getPercentRate());
        entity.setPaid(request.getPaid());
        entity.setTheTotalAmountYouPay(request.getTheTotalAmountYouPay());
        entity.setCountMonthsToPay(request.getCountMonthsToPay());
        entity.setBankProfit(request.getBankProfit());
        entity.setHowMuchIsTheLoan(request.getHowMuchIsTheLoan());
        entity.setPaymentPerMonth(request.getPaymentPerMonth());
        entity.setIdUser(request.getIdUser());

        return entity;
    }

    private UpdateCreditResponse convert(Optional<CreditEntity> entityOptional) {
        UpdateCreditResponse response = new UpdateCreditResponse();

        if (entityOptional.isPresent()) {
            CreditEntity entity = entityOptional.get();
            response = new UpdateCreditResponse(entity.getIdCredit(), entity.getHowMuchToPay()
                    , entity.getPercentRate(), entity.getPaid(), entity.getTheTotalAmountYouPay()
                    , entity.getCountMonthsToPay(), entity.getBankProfit(), entity.getHowMuchIsTheLoan()
                    , entity.getPaymentPerMonth(), entity.getIdUser());
        }
        return response;
    }
}
