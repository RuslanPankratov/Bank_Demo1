package bank.demo.dto.controller;


import bank.demo.dto.domain.Credit;
import bank.demo.dto.domain.CreditCard;
import bank.demo.dto.domain.User;
import bank.demo.dto.dto.UserDTO;
import bank.demo.dto.dto.bd.FindAllUser;
import bank.demo.dto.repository.HibernateCredit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class CreditController {

    private HibernateCredit hibernateCredit;

    @GetMapping("/creditFind")
    public List<Credit> findAllCreditCard() {
        return hibernateCredit.findAll();
    }



    @GetMapping("/findCredit/{id}")
    public Optional<Credit> findById(@PathVariable("id") Integer id) {
        return hibernateCredit.findById(id);
    }


    @PostMapping("/createCredit")
    public Credit create(@RequestBody Credit credit){ //нельзя использовать напрямую юзера, иначе можно записать
        // ошибочные данные, но если мы запишем ошибочные, юзер просто не будет создан
        hibernateCredit.save(credit);
        return credit;
    }



}
