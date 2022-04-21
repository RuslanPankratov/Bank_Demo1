package bank.demo.dto.bd.services;

import bank.demo.dto.domain.Credit;
import bank.demo.dto.domain.User;
import bank.demo.dto.repository.HibernateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditService {
    private final HibernateRepository<Credit> createUser;

    public void creditCreate(){
         Credit credit = new Credit();

    }

}
