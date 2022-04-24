package bank.demo.dto.controller;

import bank.demo.dto.core.FindAllUserServise;
import bank.demo.dto.dto.bd.FindAllUser;


import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class UserController {

    //обязательно запускай через гредел а не интеледжи свой проект
    private final FindAllUserServise findAllUserServise;

    @GetMapping("/user1")
    public FindAllUser findAllUsers() {
        return findAllUserServise.findAll();
    }
}
