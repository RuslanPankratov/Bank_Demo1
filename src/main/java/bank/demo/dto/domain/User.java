package bank.demo.dto.domain;


import bank.demo.dto.enum_class.TypeOfBenefits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;

@Entity(name = "users")//для того, чтобы потом использовать в коде это будет как ссылка на этот класс, когда будем работать с бд
@Table(name = "user")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")  //надо писать через нижнее подчёркивание, чтобы всё работало, потому что такая стратегия
    private Integer idUser;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    // @Enumerated(EnumType.STRING)//так можно подключать энамы

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_benefits")
    private TypeOfBenefits typeOfBenefits;
//    @Column(name = "id_bank_account")
//    private int idBankAccount;
    //firstName
    //lastName
    //age
    //typeOfBenefits




}
