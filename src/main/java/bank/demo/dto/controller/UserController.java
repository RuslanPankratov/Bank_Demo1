package bank.demo.dto.controller;

import bank.demo.dto.bd.services.UserService1;
import bank.demo.dto.core.FindAllUserServise;
import bank.demo.dto.domain.Credit;
import bank.demo.dto.domain.CreditCard;
import bank.demo.dto.domain.User;
import bank.demo.dto.dto.UserDTO;
import bank.demo.dto.dto.bd.FindAllUser;


import bank.demo.dto.repository.HibernateCredit;
import bank.demo.dto.repository.HibernateCreditCard;
import bank.demo.dto.repository.HibernateUser;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class UserController {

    //обязательно запускай через гредел а не интеледжи свой проект
    private final FindAllUserServise findAllUserServise;
    private UserService1 userService1;

    private HibernateUser hibernateUser;





    @GetMapping("/findUser")
    public FindAllUser findAllUsers() {
        return findAllUserServise.findAll();
    }

    @GetMapping("/findUser/{id}")
    public Optional<User> findById(@PathVariable("id") Integer id) {
        return hibernateUser.findById(id);
    }


    @PostMapping("/createUser")
    public UserDTO create(@RequestBody UserDTO userDTO){ //нельзя использовать напрямую юзера, иначе можно записать
        // ошибочные данные, но если мы запишем ошибочные, юзер просто не будет создан
        userService1.add(userDTO);
        return userDTO;
    }



    @PostMapping("/updateUser")
    public User update(@RequestBody User user){ //надо понять как можно обновлять
        hibernateUser.update(user);
        return user;
    }








    //один запрос, один ввод, без логинов и паролей
    //я могу всё сделать одним запросом, создать юзера с кредитной картой, а там по дефолту все карты, без возможности
    // создавать новые карты

//    @PostMapping("/user1")
//    public Names add(@RequestBody Names request) {//(@RequestBody Names request) здесь надо передевать даные, которые
//        // будут в поля names. Поля не будут работать без гетеров и сетеров, иначе будет ошибка
//        return request;
//    }




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
