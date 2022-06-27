package bank.core.service.credit;

import bank.domain.CreditEntity;
import bank.dto.credit.CreditDTO;
import bank.dto.credit.find.GetByIdCreditResponse;
import bank.repository.CreditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class GetCreditByIdService {

    private final CreditRepository repository;

    public GetByIdCreditResponse getCreditById(Integer id) {
        GetByIdCreditResponse getByIdCreditResponse = repository.findById(id)
                .map(this::convert)
                .map(GetByIdCreditResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("Credit Card with id " + id + "is not found."));

        log.debug("Return Get By Id Credit Card Response: {}", getByIdCreditResponse);

        return getByIdCreditResponse;

    }

    private CreditDTO convert(CreditEntity entity) {
        return new CreditDTO(entity.getIdCredit(), entity.getHowMuchToPay()
                , entity.getPercentRate(), entity.getPaid(), entity.getTheTotalAmountYouPay()
                , entity.getCountMonthsToPay(), entity.getBankProfit(), entity.getHowMuchIsTheLoan()
                , entity.getPaymentPerMonth(), entity.getIdUser());
    }
}
