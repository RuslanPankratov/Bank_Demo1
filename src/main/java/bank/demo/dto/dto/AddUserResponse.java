package bank.demo.dto.dto;

import bank.demo.dto.core.validation.CoreError;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AddUserResponse {

    private Integer createdUserId;
    private List<CoreError> errors;
}
