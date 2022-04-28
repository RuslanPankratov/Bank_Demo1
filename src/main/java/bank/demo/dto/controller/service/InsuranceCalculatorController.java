package bank.demo.dto.controller.service;


import bank.demo.dto.bd.services.BankAccountService;
import bank.demo.dto.domain.BankAccount;
import bank.demo.dto.domain.Insurance;
import bank.demo.dto.helper.InsuranceCalculatorHelper;
import bank.demo.dto.services.InsuranceCalculator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController //сокращение написания контролера и ещё одной анотации
@AllArgsConstructor
public class InsuranceCalculatorController {

    private InsuranceCalculator insuranceCalculator;
    private BankAccountService bankAccountService;

    @PostMapping("/insuranceCalculator")
    public BankAccount insurance(@RequestBody InsuranceCalculatorHelper helper) { //я передам тут один класс, в котором будут разные поля,
        // потому что нельзя передавать несколько классов, можно конечно сделать интерфейс, который будет переносить
        // несколько типов класса

       BankAccount bankAccount = bankAccountService.getBankAccount(helper.getId());
      insuranceCalculator.insurance(bankAccount, helper.getSum(),helper.getTypeInsurance());
       return bankAccount;
    }

}
