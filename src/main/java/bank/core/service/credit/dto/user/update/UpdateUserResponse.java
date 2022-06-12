package bank.core.service.credit.dto.user.update;

import bank.enum_class.TypeOfBenefits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResponse {

    private String firstName;
    private String lastName;
    private int age;
    private TypeOfBenefits typeOfBenefits;
    private int idUser;

}
