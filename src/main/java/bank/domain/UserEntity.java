package bank.domain;


import bank.enum_class.TypeOfBenefits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;

@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_benefits")
    private TypeOfBenefits typeOfBenefits;

}
