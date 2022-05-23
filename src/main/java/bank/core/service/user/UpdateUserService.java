package bank.core.service.user;


import bank.domain.UserEntity;
import bank.dto.user.UpdateUserRequest;

import bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@AllArgsConstructor
@Component
public class UpdateUserService {
    private final UserRepository repository;

    @Transactional
    public void update(UpdateUserRequest request){
        repository.findById(request.getIdUser())
                .map(user -> updateFields(user, request))
                .ifPresent(repository::save);
    }


    private UserEntity updateFields(UserEntity entity, UpdateUserRequest request){
        entity.setIdUser(entity.getIdUser());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setAge(request.getAge());
        entity.setTypeOfBenefits(request.getTypeOfBenefits());

        log.debug("Update User Entity: {}", entity);

        return entity;
    }

}
