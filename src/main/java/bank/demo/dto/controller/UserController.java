package bank.demo.dto.controller;

import bank.demo.dto.bd.services.BankService;
import bank.demo.dto.bd.services.UserService;
import bank.demo.dto.bd.services.UserService1;
import bank.demo.dto.core.FindAllUserServise;
import bank.demo.dto.domain.BankAccount;
import bank.demo.dto.domain.CreditCard;
import bank.demo.dto.domain.User;
import bank.demo.dto.dto.AddUserResponse;
import bank.demo.dto.dto.CreditCardDTO;
import bank.demo.dto.dto.UserDTO;
import bank.demo.dto.dto.bd.FindAllUser;


import bank.demo.dto.repository.HibernateCreditCard;
import bank.demo.dto.repository.HibernateUser;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController() //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class UserController {

    //обязательно запускай через гредел а не интеледжи свой проект
    private final FindAllUserServise findAllUserServise;
    private UserService1 userService1;
    private HibernateUser hibernateUser;
    private HibernateCreditCard hibernateCreditCard;
    private BankService bankService;

    @GetMapping("/user")
    public FindAllUser findAllUsers() {
        return findAllUserServise.findAll();
    }


    @GetMapping("/creditCard")
    public List<CreditCard> findAllCreditCard() {
        return hibernateCreditCard.findAll();
    }

    @GetMapping("/create")
    public List<BankAccount> bankAccountList(){
        return bankService.loadBankAccount();
    }



    //один запрос, один ввод, без логинов и паролей

//    @PostMapping("/user1")
//    public Names add(@RequestBody Names request) {//(@RequestBody Names request) здесь надо передевать даные, которые
//        // будут в поля names. Поля не будут работать без гетеров и сетеров, иначе будет ошибка
//        return request;
//    }

    @PostMapping("/create1")
    public CreditCard create1(@RequestBody CreditCard creditCard){ //нельзя использовать напрямую юзера, иначе можно записать
        // ошибочные данные, но если мы запишем ошибочные, юзер просто не будет создан
        hibernateCreditCard.save(creditCard);
        return creditCard;
    }

    // @Column(name = "login")
    //    private String login;
    //    @Column(name = "password")
    //    private String password;
    //    @Column(name = "invoiceAmount")
    //    private BigDecimal invoiceAmount; //сума не счету
    //    @Column(name = "withdrawalLimit")
    //    private BigDecimal withdrawalLimit;
    @PostMapping("/create")
    public UserDTO create(@RequestBody UserDTO userDTO){ //нельзя использовать напрямую юзера, иначе можно записать
   // ошибочные данные, но если мы запишем ошибочные, юзер просто не будет создан
        userService1.add(userDTO);
        return userDTO;
    }


//    @PostMapping("/create")
//    public User create(@RequestBody User user){ //можем сделать и так, но чтобы не было ошибок
//       hibernateUser.save(user);
//        return user;
//    }

    //    "firstName" : "Ruslan",
    //    "lastName" : "Pankratov",
    //    "age" : "20"
    //    "typeOfBenefits" : "pens"

}
