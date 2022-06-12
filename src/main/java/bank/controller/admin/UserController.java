package bank.controller.admin;

import bank.core.service.user.AddUserService;
import bank.core.service.user.FindAllUserService;
import bank.core.service.user.GetUserByIdService;
import bank.core.service.user.UpdateUserService;

import bank.core.service.credit.dto.user.add.AddUserRequest;
import bank.core.service.credit.dto.user.add.AddUserResponse;
import bank.core.service.credit.dto.user.find.FindAllUserResponse;
import bank.core.service.credit.dto.user.find.GetByIdUserResponse;
import bank.core.service.credit.dto.user.update.UpdateUserRequest;
import bank.core.service.credit.dto.user.update.UpdateUserResponse;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private FindAllUserService findAllUserService;
    private GetUserByIdService getUserByIdService;
    private AddUserService addUserService;
    private UpdateUserService updateUserService;

    @GetMapping()
    public FindAllUserResponse findAllUsers() {
        log.debug("Find All User received");
        return findAllUserService.findAll();
    }

    @GetMapping("/{id}")
    public GetByIdUserResponse findById(@PathVariable("id") Integer id) {
        log.debug("Find By Id Request Received, id: {}", id);
        return getUserByIdService.getUserById(id);
    }

    @PostMapping()
    public AddUserResponse create(@RequestBody @Valid AddUserRequest request) {
        log.debug("Received Add User request: {}", request);
        return addUserService.add(request);
    }

    @PutMapping()
    public UpdateUserResponse updateUser(@RequestBody @Valid UpdateUserRequest request) {
        log.debug("Received update User request: {}", request);
        return updateUserService.update(request);
    }

}
