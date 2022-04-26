package bank.demo.dto.services;

import bank.demo.dto.dto.BankAccountDTO;
import org.springframework.stereotype.Component;

@Component
public class PayForInsurance {


    public void payInsurance(BankAccountDTO bankAccount) {
        if (bankAccount.getInsurance().getInsurancePaid() < bankAccount.getCreditCard().getInvoiceAmount()) {
            bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount()
                    - bankAccount.getInsurance().getInsurancePaid());
            bankAccount.getInsurance().setInsurancePaid(0);
        }
    }
}
