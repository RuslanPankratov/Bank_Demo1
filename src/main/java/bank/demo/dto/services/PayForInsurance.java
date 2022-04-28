package bank.demo.dto.services;

import bank.demo.dto.domain.BankAccount;
import bank.demo.dto.dto.BankAccountDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PayForInsurance {


//    public void payInsurance(BankAccountDTO bankAccount) {
//        if (bankAccount.getInsurance().getInsurancePaid() < bankAccount.getCreditCard().getInvoiceAmount()) {
//            bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().getInvoiceAmount()
//                    - bankAccount.getInsurance().getInsurancePaid());
//            bankAccount.getInsurance().setInsurancePaid(0);
//        }
//    }


//    public void payInsurance(BankAccount bankAccount) {
//        if (bankAccount.getInsurance().getInsurancePaid().compareTo(bankAccount.getCreditCard().getInvoiceAmount()) < 0) {
//
//            BigDecimal bigDecimal =  bankAccount.getCreditCard().getInvoiceAmount().subtract( bankAccount.getInsurance().getInsurancePaid().negate());
//            bankAccount.getCreditCard().setInvoiceAmount(bankAccount.getCreditCard().setInvoiceAmount(
//                bigDecimal   ));
//            bankAccount.getInsurance().setInsurancePaid(0);
//        }
//    }
}
