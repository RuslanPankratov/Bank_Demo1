package bank.core.service.user;

import bank.domain.UserEntity;
import bank.dto.user.update.UpdateUserRequest;
import bank.dto.user.update.UpdateUserResponse;
import bank.enum_class.TypeOfBenefits;
import bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UpdateUserService updateUserService;


    @Test
    void shouldUpdateUserTest() {

        when(userRepository.findById(1)).thenReturn(Optional.of(entity(1)));

        UpdateUserRequest updateUserRequest = request();

        UpdateUserResponse result = updateUserService.update(updateUserRequest);
        UpdateUserResponse expectedResult = convert();

        assertEquals(expectedResult, result);

        verify(userRepository).save(any());
        verify(userRepository, times(2)).findById(any());
        // иначе будет ошибка

        verifyNoMoreInteractions(userRepository);
    }


    private UpdateUserRequest request() {
        return new UpdateUserRequest("Ruslan", "Pankratov", 25, TypeOfBenefits.NO_BENEFITS
                , 1);
    }

    private UserEntity entity(Integer id) {
        UserEntity user = new UserEntity();
        user.setIdUser(id);
        user.setFirstName("Ruslan");
        user.setLastName("Pankratov");
        user.setAge(25);
        user.setTypeOfBenefits(TypeOfBenefits.NO_BENEFITS);
        return user;
    }

    private UpdateUserResponse convert() {
        return new UpdateUserResponse("Ruslan", "Pankratov", 25
                , TypeOfBenefits.NO_BENEFITS, 1);
    }

}