package bank.demo.dto.services.cardEnter;

import bank.demo.dto.dto.ListBankAccount;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(5)
@Component
public class ExitCardEntryIMPL implements CardEntryIMPL {

    @Override
    public void menu(ListBankAccount listBankAccount, int i) {
        System.out.println("Bye-bye");
        System.exit(0);//делает завершение всей программы
    }

    @Override
    public String getActionName() {
        return "Exit";
    }
}
