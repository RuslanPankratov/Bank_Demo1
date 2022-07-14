package bank.dto.user.find;

import bank.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdUserResponse {

    private UserDTO user;

}
