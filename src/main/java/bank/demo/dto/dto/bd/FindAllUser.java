package bank.demo.dto.dto.bd;

import bank.demo.dto.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllUser {

    private List<UserDTO> users;


}
