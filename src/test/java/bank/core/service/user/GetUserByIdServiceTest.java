package bank.core.service.user;

import bank.domain.UserEntity;
import bank.dto.user.find.GetByIdUserResponse;
import bank.dto.user.UserDTO;
import bank.enum_class.TypeOfBenefits;
import bank.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetUserByIdServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private GetUserByIdService getUserByIdService;

    @Test
    void findTest() {
        UserEntity returnResult = entities();
        when(repository.findById(1)).thenReturn(Optional.of(returnResult));

        GetByIdUserResponse result = getUserByIdService.getUserById(1);

        GetByIdUserResponse expectedResult = new GetByIdUserResponse();
        expectedResult.setUser(response());

        assertEquals(expectedResult, result);

        verify(repository).findById(anyInt());

        verifyNoMoreInteractions(repository);
    }

    private UserEntity entities() {
        UserEntity user = new UserEntity();
        user.setIdUser(1);
        user.setFirstName("Ruslan");
        user.setLastName("Pankratov");
        user.setAge(25);
        user.setTypeOfBenefits(TypeOfBenefits.NO_BENEFITS);
        return user;
    }

    private UserDTO response() {
        return new UserDTO("Ruslan", "Pankratov", 25
                , TypeOfBenefits.NO_BENEFITS, 1);
    }

}