package bank.demo.dto.bd.services;

import bank.demo.dto.domain.User;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.repository.HibernateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

      private final HibernateRepository<User> createUser;


   public User userCreate(String firstName, String lastName, int age, String typeOfBenefits){
        User users = new User();

        users.setFirstName(firstName);
        users.setLastName(lastName);
        users.setAge(age);
        users.setTypeOfBenefits(typeOfBenefits);//можно через ту стринг это сделать
        //юзер уже существует, теперь просто для начала подключимся к бд
        createUser.save(users);//здесь он сохранит моего юзера, значит надо

        //если в этом месте передавать юзера, тогда можно без дженейрика всё это сделать
        //первое, мне нужен этот юзер в будущем, чтобы работать с ним, и сделать список листа банк аккаунтов
        //это означает что мне нужен класс, где я создам моего юзера и тут его верну и просто соберу в список аккаунтов
        // и в тоже время, он должен сохранить все данные
       return users;
    }

}
