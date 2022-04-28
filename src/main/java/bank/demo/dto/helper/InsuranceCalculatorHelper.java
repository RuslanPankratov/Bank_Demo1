package bank.demo.dto.helper;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InsuranceCalculatorHelper {

    private int id;
    private BigDecimal sum;
    private String typeInsurance;

}
