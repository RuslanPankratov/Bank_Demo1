package bank.demo.dto.services;

import bank.demo.dto.dto.BankAccount;
import org.springframework.stereotype.Component;
@Component
public class PayForInsurance {


    public void payInsurance(BankAccount bankAccount) {
        if (bankAccount.getInsurance().getInsurancePaid() < bankAccount.getCreditCard().getInvoiceAmount()) {
            bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount()
                    - bankAccount.getInsurance().getInsurancePaid()); //отнимаем с карты и убераем с долга сколько надо заплатить
            bankAccount.getInsurance().setInsurancePaid(0);
        }
    }
}
