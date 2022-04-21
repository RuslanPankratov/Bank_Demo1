package bank.demo.dto.dto;

import bank.demo.dto.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindAllUser {

    private List<Users1> users;


}
