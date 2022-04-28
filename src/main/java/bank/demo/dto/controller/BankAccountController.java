package bank.demo.dto.controller;


import bank.demo.dto.bd.services.BankService;
import bank.demo.dto.domain.BankAccount;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class BankAccountController {


    @Autowired
    private SessionFactory sessionFactory;

    private BankService bankService;

    @GetMapping("/create")
    public List<BankAccount> bankAccountList(){
        return bankService.loadBankAccount();
    }

}
