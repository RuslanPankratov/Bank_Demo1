package bank.demo.dto.core.validation.error.errorUser;

import bank.demo.dto.dto.UserDTO;

public interface ErrorUser {

    void validate(UserDTO userDTO);
}
