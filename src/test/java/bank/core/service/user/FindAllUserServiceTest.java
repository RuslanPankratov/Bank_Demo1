package bank.core.service.user;

import bank.domain.UserEntity;
import bank.dto.user.find.FindAllUserResponse;
import bank.dto.user.UserDTO;
import bank.enum_class.TypeOfBenefits;
import bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class FindAllUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FindAllUserService findAllUserService;

    @Test
    void findTest() {

        List<UserEntity> returnResult = entities();

        when(userRepository.findAll()).thenReturn(returnResult);

        FindAllUserResponse result = findAllUserService.findAll();
        FindAllUserResponse expectedResult = new FindAllUserResponse(response());

        assertEquals(expectedResult, result);


    }

    private List<UserEntity> entities() {
        UserEntity user = new UserEntity();
        user.setIdUser(1);
        user.setFirstName("Ruslan");
        user.setLastName("Pankratov");
        user.setAge(25);
        user.setTypeOfBenefits(TypeOfBenefits.NO_BENEFITS);
        return List.of(user);
    }

    private List<UserDTO> response() {
        UserDTO dto = new UserDTO("Ruslan", "Pankratov", 25, TypeOfBenefits.NO_BENEFITS,1);
        return List.of(dto);
    }
}