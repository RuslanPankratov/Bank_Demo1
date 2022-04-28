package bank.demo.dto.dto;

import bank.demo.dto.enum_class.TypeOfBenefits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private int age;
    private TypeOfBenefits typeOfBenefits;
    private int idUser;



    @Override
    public String toString() {
        return "UserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", typeOfBenefits='" + typeOfBenefits + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
