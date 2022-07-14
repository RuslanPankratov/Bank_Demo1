package bank.core.service.user;

import bank.domain.UserEntity;
import bank.dto.user.find.FindAllUserResponse;
import bank.dto.user.UserDTO;
import bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class FindAllUserService {
    private final UserRepository repository;


    public FindAllUserResponse findAll(){

        List<UserDTO> dtos = repository.findAll().stream()
                .map(this::convert)
                .toList();

        FindAllUserResponse findAllUserResponse = new FindAllUserResponse(dtos);

        log.debug("Find All Credit Card: {}", dtos);
        log.debug("Find All Credit Card: {}", findAllUserResponse);

        return findAllUserResponse;
    }


    private UserDTO convert(UserEntity user){
        return new UserDTO(user.getFirstName(),
                user.getLastName(), user.getAge(), user.getTypeOfBenefits(),user.getIdUser());
    }

}
