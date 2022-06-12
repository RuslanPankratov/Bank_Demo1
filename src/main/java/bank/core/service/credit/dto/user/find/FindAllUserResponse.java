package bank.core.service.credit.dto.user.find;

import bank.core.service.credit.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor

public class FindAllUserResponse {

    @NotNull
    private List<UserDTO> users;

}
