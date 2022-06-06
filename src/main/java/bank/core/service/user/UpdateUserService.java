package bank.core.service.user;


import bank.domain.UserEntity;
import bank.dto.user.update.UpdateUserRequest;

import bank.dto.user.update.UpdateUserResponse;
import bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Component
public class UpdateUserService {
    private UserRepository repository;

    @Transactional
    public UpdateUserResponse update(UpdateUserRequest request) {
        repository.findById(request.getIdUser())
                .map(user -> updateFields(user, request))
                .ifPresent(repository::save);

        Optional<UserEntity> userEntity = repository.findById(request.getIdUser());

        log.debug("Return Update User Response: {}", userEntity);

        return convert(userEntity);
    }


    private UserEntity updateFields(UserEntity entity, UpdateUserRequest request) {
        entity.setIdUser(entity.getIdUser());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAge(request.getAge());
        entity.setTypeOfBenefits(request.getTypeOfBenefits());

        log.debug("Update User Entity: {}", entity);

        return entity;
    }

    private UpdateUserResponse convert(Optional<UserEntity> entityOptional) {
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();

        if (entityOptional.isPresent()) {
            UserEntity entity = entityOptional.get();
            updateUserResponse = new UpdateUserResponse(entity.getFirstName(), entity.getLastName(), entity.getAge(),
                    entity.getTypeOfBenefits(), entity.getIdUser());
        }
        return updateUserResponse;
    }

}
