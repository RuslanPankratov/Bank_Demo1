package bank.controller.admin;

import bank.core.service.user.AddUserService;
import bank.core.service.user.FindAllUserService;
import bank.core.service.user.GetUserByIdService;
import bank.core.service.user.UpdateUserService;

import bank.domain.UserEntity;
import bank.dto.user.*;
import bank.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
public class UserController {

    private FindAllUserService findAllUserService;
    private GetUserByIdService getUserByIdService;
    private AddUserService addUserService;
    private UpdateUserService updateUserService;
    private UserRepository userRepository;

    @GetMapping("/user/{name}")
    public Optional<UserEntity> findUser(@PathVariable("name") String name) {
        log.debug("Find for name User Entity received: {}", name);
        return userRepository.findFirstByFirstName(name);
    }

    @GetMapping("/user")
    public FindAllUserResponse findAllUsers() {
        log.debug("Find All User received");
        return findAllUserService.findAll();
    }

    @GetMapping("/user/{id}")
    public GetByIdUserResponse findById(@PathVariable("id") Integer id) {
        log.debug("Find By Id Request Received, id: {}", id);
        return getUserByIdService.getUserById(id);
    }

    @PostMapping("/user")
    public AddUserResponse create(@RequestBody @Valid AddUserRequest request) {
        log.debug("Received Add User request: {}", request);
        return addUserService.add(request);
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody @Valid UpdateUserRequest request) {
        log.debug("Received update User request: {}", request);
        updateUserService.update(request);
    }

}
