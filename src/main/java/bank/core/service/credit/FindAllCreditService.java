package bank.core.service.credit;

import bank.domain.CreditEntity;
import bank.dto.credit.CreditDTO;
import bank.dto.credit.find.FindAllCreditResponse;
import bank.repository.CreditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class FindAllCreditService {

    private final CreditRepository repository;

    public FindAllCreditResponse findAll() {
        List<CreditDTO> dtos = repository.findAll().stream()
                .map(this::convert)
                .toList();

        FindAllCreditResponse findAllCreditResponse = new FindAllCreditResponse(dtos);

        log.debug("Find All Credit: {}", dtos);
        log.debug("Find All Credit: {}", findAllCreditResponse);

        return findAllCreditResponse;
    }


    private CreditDTO convert(CreditEntity entity) {
        return new CreditDTO(entity.getIdCredit(), entity.getHowMuchToPay()
                , entity.getPercentRate(), entity.getPaid(), entity.getTheTotalAmountYouPay()
                , entity.getCountMonthsToPay(), entity.getBankProfit(), entity.getHowMuchIsTheLoan()
                , entity.getPaymentPerMonth(), entity.getIdUser());
    }
}

