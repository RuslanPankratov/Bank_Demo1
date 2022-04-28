package bank.demo.dto.bd.services;

import bank.demo.dto.core.validation.error.errorUser.rule.age.RuleAge;
import bank.demo.dto.core.validation.error.errorUser.rule.name.RuleFirstNameAndLastName;
import bank.demo.dto.domain.Credit;
import bank.demo.dto.domain.Insurance;
import bank.demo.dto.domain.User;
import bank.demo.dto.dto.UserDTO;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.repository.HibernateCredit;
import bank.demo.dto.repository.HibernateInsurance;
import bank.demo.dto.repository.HibernateRepository;
import bank.demo.dto.repository.HibernateUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService1 {
    @Autowired
    private List<RuleFirstNameAndLastName> ruleFirstNameAndLastNameList;
    @Autowired
    private List<RuleAge> ruleAges;
    private final HibernateUser hibernateUser;
    private final HibernateInsurance hibernateInsurance;
    private final HibernateCredit hibernateCredit;

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
              // TypeOfBenefits typeOfBenefits = TypeOfBenefits.DISABILITY_ONE_TWO;
               user.setTypeOfBenefits(userDTO.getTypeOfBenefits());
               hibernateUser.save(user);


//                Insurance insurance = new Insurance();
//                insurance.setIdInsurance(user.getIdUser());
//                insurance.setInsurancePaid(new BigDecimal(0));
//                insurance.setSumInsured(new BigDecimal(0));
//                hibernateInsurance.save(insurance);
//
//                Credit credit = new Credit();
//                credit.setIdCredit(user.getIdUser());
//                credit.setHowMuchToPay(new BigDecimal(0));
//                credit.setPercentRate(new BigDecimal(0));
//                credit.setPaid(new BigDecimal(0));
//                credit.setTheTotalAmountYouPay(new BigDecimal(0));
//                credit.setCountMonthsToPay(new BigDecimal(0));
//                credit.setBankProfit(new BigDecimal(0));
//                credit.setHowMuchIsTheLoan(new BigDecimal(0));
//                credit.setPaymentPerMonth(new BigDecimal(0));
//                hibernateCredit.save(credit);

            }
        }




    }
}
