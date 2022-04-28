package bank.demo.dto.controller;


import bank.demo.dto.domain.Credit;
import bank.demo.dto.domain.CreditCard;
import bank.demo.dto.repository.HibernateCreditCard;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class CreditCardController {

       private final HibernateCreditCard hibernateCreditCard;


    @GetMapping("/creditCardFind")
    public List<CreditCard> findAllCreditCard() {
        return hibernateCreditCard.findAll();
    }



    @GetMapping("/findCreditCard/{id}")
    public Optional<CreditCard> findById(@PathVariable("id") Integer id) {
        return hibernateCreditCard.findById(id);
    }


    @PostMapping("/createCreditCard")
    public CreditCard create(@RequestBody CreditCard creditCard){ //нельзя использовать напрямую юзера, иначе можно записать
        // ошибочные данные, но если мы запишем ошибочные, юзер просто не будет создан
        hibernateCreditCard.save(creditCard);
        return creditCard;
    }

}
