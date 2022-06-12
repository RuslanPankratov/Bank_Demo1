package bank.core.service.credit.dto.user;

import bank.enum_class.TypeOfBenefits;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor

public class UserDTO {

    @NotBlank
    @Length(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Length(min = 2, max = 20)
    private String lastName;
    @Range(min = 18)
    @NotNull
    private int age;
    @NotNull
    private TypeOfBenefits typeOfBenefits;
    @Range(min = 1)
    @NotNull
    private int idUser;

}
