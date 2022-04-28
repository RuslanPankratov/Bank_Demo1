package bank.demo.dto.bd.services;


import bank.demo.dto.core.validation.CoreError;
import bank.demo.dto.core.validation.ValidationServiceUser;
import bank.demo.dto.domain.User;
import bank.demo.dto.dto.AddUserResponse;
import bank.demo.dto.dto.UserDTO;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.repository.HibernateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {

      private final HibernateRepository<User> createUser;

      private ValidationServiceUser validationService;
    //попробуй лучше это всё сделать через список рулов
      //юзер теперь может меняться
   public AddUserResponse userCreate(UserDTO userDTO){
           List<CoreError> validationResult = validationService.validate(userDTO);
           if (validationResult.isEmpty()){
               AddUserResponse addUserResponse = new AddUserResponse();
               addUserResponse.setErrors(validationResult);
               return addUserResponse;
           } //здесь будет счёт сколько создано юзеров и зачем-то передан список ошибок





       //принимая дто юзера, я должен возвращать настоящего юзера
       User user = convert(userDTO);
       // user.setIdUser(userDTO.getIdUser());
       User createUsers = createUser.save(user);
       AddUserResponse addUserResponse = new AddUserResponse();
       addUserResponse.setCreatedUserId(createUsers.getIdUser());
       System.out.println("Sending response: " + addUserResponse);

       // createUser.save(user);//здесь он сохранит моего юзера, значит надо
        //если в этом месте передавать юзера, тогда можно без дженейрика всё это сделать
        //первое, мне нужен этот юзер в будущем, чтобы работать с ним, и сделать список листа банк аккаунтов
        //это означает что мне нужен класс, где я создам моего юзера и тут его верну и просто соберу в список аккаунтов
        // и в тоже время, он должен сохранить все данные
       return addUserResponse;
    }


    User convert(UserDTO userDTO){
       User user = new User();
       user.setFirstName(userDTO.getFirstName());
       user.setLastName(userDTO.getLastName());
       user.setAge(userDTO.getAge());
       user.setTypeOfBenefits(userDTO.getTypeOfBenefits());
       return user;
    }

}
