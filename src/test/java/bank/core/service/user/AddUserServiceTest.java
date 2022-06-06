package bank.core.service.user;

import bank.domain.UserEntity;
import bank.dto.user.add.AddUserRequest;
import bank.dto.user.add.AddUserResponse;
import bank.enum_class.TypeOfBenefits;
import bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AddUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AddUserService addUserService;


    @Test
    void saveTest() {

        when(userRepository.save(entity(null))).thenReturn(entity(1001));

        AddUserRequest request = createAddUserRequest();

        AddUserResponse result = addUserService.add(request);
        AddUserResponse expectedResult = new AddUserResponse(1001);

        assertEquals(expectedResult, result);

        verify(userRepository).save(any());

        verifyNoMoreInteractions(userRepository);
    }

    private AddUserRequest createAddUserRequest() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setFirstName("Ruslan");
        addUserRequest.setLastName("Pankratov");
        addUserRequest.setAge(25);
        addUserRequest.setTypeOfBenefits(TypeOfBenefits.NO_BENEFITS);
        return addUserRequest;
    }

    private UserEntity entity(Integer id) {
        return new UserEntity(id, "Ruslan", "Pankratov", 25,
                TypeOfBenefits.NO_BENEFITS);
    }

}