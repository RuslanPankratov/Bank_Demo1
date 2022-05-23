package bank.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@Data
public class AddUserResponse {
    @Range(min = 1)
    private Integer createdUserId;

}
