package bank.demo.dto.services;

//import bank.demo.dto.bd.delete.DB;
import bank.demo.dto.domain.BankAccount;
import bank.demo.dto.domain.Insurance;
import bank.demo.dto.dto.BankAccountDTO;
import bank.demo.dto.dto.InsuranceDTO;
import bank.demo.dto.repository.HibernateInsurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

//@AllArgsConstructor
@Service
@Data
@AllArgsConstructor
public class InsuranceCalculator {

    @Autowired
    private HibernateInsurance hibernateInsurance;

    public void insurance(BankAccount bankAccount, BigDecimal sum, String typeInsurance) {

        if (typeInsurance.equalsIgnoreCase("house")) {
            houses(bankAccount, sum);
        } else if (typeInsurance.equalsIgnoreCase("items")) {
            items(bankAccount, sum);
        } else if (typeInsurance.equalsIgnoreCase("health")) {
            health(bankAccount, sum);
        } else if (typeInsurance.equalsIgnoreCase("car")) {
            car(bankAccount, sum);
        }

    }


    private void houses(BankAccount bankAccount, BigDecimal sum) {
        calculate(bankAccount, sum, new BigDecimal(200));
    }

    private void items(BankAccount bankAccount, BigDecimal sum) {
        calculate(bankAccount, sum, new BigDecimal(100));
    }

    private void health(BankAccount bankAccount, BigDecimal sum) {
        calculate(bankAccount, sum, new BigDecimal(110));
    }

    private void car(BankAccount bankAccount, BigDecimal sum) {
        calculate(bankAccount, sum, new BigDecimal(20));
    }


    private void calculate(BankAccount bankAccount, BigDecimal sum, BigDecimal percent) {
        BigDecimal howMuchToPay = sum.divide(percent);
      //  howMuchToPay.setScale(3, RoundingMode.UP);
       // 111.5551 -> setScale(3, ROUND_UP)

       bankAccount.getInsurance().setIdInsurance(bankAccount.getUser().getIdUser());
       BigDecimal sumInsured = bankAccount.getInsurance().getSumInsured().add(sum);
        bankAccount.getInsurance().setSumInsured(sumInsured);
        BigDecimal paid = bankAccount.getInsurance().getInsurancePaid().add(howMuchToPay);
        bankAccount.getInsurance().setInsurancePaid(paid);

        hibernateInsurance.update(bankAccount.getInsurance());
    }
}
