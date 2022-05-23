package bank.core.service.user;


import bank.domain.UserEntity;
import bank.dto.user.AddUserRequest;
import bank.dto.user.AddUserResponse;
import bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddUserService {

    private UserRepository userRepository;


    public AddUserResponse add(AddUserRequest request) {
        var entity = convert(request);
        entity.setIdUser(request.getIdUser());
        var createdEntity = userRepository.save(entity);
        var response = new AddUserResponse();
        response.setCreatedUserId(createdEntity.getIdUser());

        log.debug("User Entity request: {}", entity);
        log.debug("Successfully saved: {}", createdEntity);
        log.debug("Sending response: {}", response);

        return response;
    }

    private UserEntity convert(AddUserRequest request) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAge(request.getAge());
        entity.setTypeOfBenefits(request.getTypeOfBenefits());
        return entity;

    }
}
