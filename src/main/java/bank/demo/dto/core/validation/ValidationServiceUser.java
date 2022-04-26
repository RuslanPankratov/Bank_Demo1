package bank.demo.dto.core.validation;

import bank.demo.dto.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ValidationServiceUser {

    private List<ValidationRule> validationRules;

    public List<CoreError> validate(UserDTO userDTO){
        List<CoreError> errors = new ArrayList<>();
        if (userDTO == null) {
            errors.add(new CoreError("ToDo must not be null"));
            return errors;
        }

        return validationRules.stream()
                .map(rule -> validate(rule, userDTO))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private CoreError validate(ValidationRule rule, UserDTO userDTO) {
        try {
            rule.validate(userDTO);
        } catch (ValidationException e) {
            return new CoreError(e.getMessage());
        }
        return null;
    }


}
