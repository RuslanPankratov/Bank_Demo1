package bank.demo.dto.core.validation;

import bank.demo.dto.dto.UserDTO;


public interface ValidationRule {

    void validate(UserDTO userDTO);

    default void checkNotNull(UserDTO entity) {
        if (entity == null) {
            throw new ValidationException("ToDo must not be null."); //это созданный нами класс, который будет выводить
            // сообщение
        }
    }
}
