package bank.demo.dto.controller;

import bank.demo.dto.bd.services.UserService;
import bank.demo.dto.core.FindAllUserServise;
import bank.demo.dto.domain.User;
import bank.demo.dto.dto.FindAllUser;
import bank.demo.dto.repository.CreateUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class UserController {

    private final FindAllUserServise findAllUserServise;
@GetMapping("/user1")
    public FindAllUser findAllUsers(){
    return findAllUserServise.findAll();

}



}
