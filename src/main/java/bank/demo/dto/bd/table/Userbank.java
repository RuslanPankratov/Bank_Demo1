package bank.demo.dto.bd.table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.*;

@Entity(name = "users")//для того, чтобы потом использовать в коде это будет как ссылка на этот класс, когда будем работать с бд
@Table(name = "userbank")
@Data
@AllArgsConstructor
@Transactional
@NoArgsConstructor

public class Userbank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer idUser;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "typeOfBenefits")
    private String typeOfBenefits;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", typeOfBenefits='" + typeOfBenefits + '\'' +
                '}';
    }
}
