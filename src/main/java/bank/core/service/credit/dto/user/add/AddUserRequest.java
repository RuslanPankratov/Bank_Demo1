package bank.core.service.credit.dto.user.add;

import bank.enum_class.TypeOfBenefits;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddUserRequest {

    @NotBlank
    @Length(min = 2, max = 20)
    private String firstName;
    @NotBlank
    @Length(min = 2, max = 20)
    private String lastName;
    @Range(min = 18)
    @NotNull
    private Integer age;
    @NotNull
    private TypeOfBenefits typeOfBenefits;

}
