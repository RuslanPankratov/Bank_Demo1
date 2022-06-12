package bank.core.service.user;

import bank.domain.UserEntity;
import bank.core.service.credit.dto.user.find.GetByIdUserResponse;
import bank.core.service.credit.dto.user.UserDTO;
import bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class GetUserByIdService {

    private final UserRepository userRepository;

    public GetByIdUserResponse getUserById(Integer id) {
        GetByIdUserResponse getByIdUserResponse = userRepository.findById(id)
                .map(this::convert)
                .map(GetByIdUserResponse::new)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + "is not found."));

        log.debug("Return Get By Id User Response: {}", getByIdUserResponse);

        return getByIdUserResponse;
    }

    private UserDTO convert(UserEntity entity) {
        return new UserDTO(entity.getFirstName(),
                entity.getLastName(), entity.getAge(), entity.getTypeOfBenefits(), entity.getIdUser());
    }
}
