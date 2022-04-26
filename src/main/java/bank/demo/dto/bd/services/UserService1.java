package bank.demo.dto.bd.services;

import bank.demo.dto.core.validation.error.errorUser.rule.age.RuleAge;
import bank.demo.dto.core.validation.error.errorUser.rule.name.RuleFirstNameAndLastName;
import bank.demo.dto.domain.User;
import bank.demo.dto.dto.UserDTO;
import bank.demo.dto.repository.HibernateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService1 {
    @Autowired
    private List<RuleFirstNameAndLastName> ruleFirstNameAndLastNameList;
    @Autowired
    private List<RuleAge> ruleAges;
    private final HibernateRepository<User> createUser;

//    public static void main(String[] args) {
//        RuleFirstNameAndLastName ruleFirstNameAndLastName = new MoreThanOneCharacter();
//        RuleFirstNameAndLastName ruleFirstNameAndLastName1 = new RuleMustContainOnlyLatinCharacters();
//        List<RuleFirstNameAndLastName> rule = new ArrayList<>();
//        rule.add(ruleFirstNameAndLastName);
//        rule.add(ruleFirstNameAndLastName1);
//
//        MoreThan moreThan = new MoreThan();
//        List<RuleAge> ruleAges = new ArrayList<>();
//        ruleAges.add(moreThan);
//         HibernateRepository<User> createUser = new HibernateUser();
//        UserService1 userService1 = new UserService1(rule,ruleAges,createUser);
//
//        UserDTO userDTO = new UserDTO("ht", "Pan", 16, "sdf", 2);
//
//        userService1.add(userDTO);
//
//    }

    public void add(UserDTO userDTO) {

        if (userDTO != null) {

            int count = 0;
//первое проверка на то, что имя не нул и в случае ошибки, прекращать приложение, или просто не допускают сохранить в бд
// этого юзера,если юзер не сохранён, тогда не сохранять и другие классы
            String firstName = "";
            String lastName = "";
            int countName = 0;
            if (userDTO.getFirstName() != null) {
                for (int i = 0; i < ruleFirstNameAndLastNameList.size(); i++) {
                    try {
                        ruleFirstNameAndLastNameList.get(i).rule(userDTO.getFirstName());
                        ruleFirstNameAndLastNameList.get(i).rule(userDTO.getLastName());
                        countName++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (countName == ruleFirstNameAndLastNameList.size()) {
                    firstName = userDTO.getFirstName();
                    lastName = userDTO.getLastName();
                    count++;
                }

                System.out.println(firstName + " после " + lastName + " " + countName);
            }

            int countAge = 0;
            for (int i = 0; i < ruleAges.size(); i++) {
                try {
                    ruleAges.get(i).rule(userDTO.getAge());
                    countAge++;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (ruleAges.size() == countAge){
                countAge = userDTO.getAge();
                count++;
            }
            System.out.println(" count age = "+ countAge);

            if (count == 2){
               User user = new User();
               user.setFirstName(userDTO.getFirstName());
               user.setLastName(userDTO.getLastName());
               user.setAge(userDTO.getAge());
               user.setTypeOfBenefits(userDTO.getTypeOfBenefits());
               createUser.save(user);

            }
        }




    }
}
