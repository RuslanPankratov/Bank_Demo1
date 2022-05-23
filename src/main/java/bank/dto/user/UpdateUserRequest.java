package bank.dto.user;

import bank.enum_class.TypeOfBenefits;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {
    @NotBlank
    @Length(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Length(min = 2, max = 20)
    private String lastName;
    @Range(min = 18)
    private int age;
    @NotNull
    private TypeOfBenefits typeOfBenefits;
    @Range(min = 1)
    private int idUser;
}
