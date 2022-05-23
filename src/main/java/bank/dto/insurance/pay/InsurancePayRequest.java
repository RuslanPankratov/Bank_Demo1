package bank.dto.insurance.pay;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class InsurancePayRequest {

   @Range(min = 1)
   private Integer idUser;
}
