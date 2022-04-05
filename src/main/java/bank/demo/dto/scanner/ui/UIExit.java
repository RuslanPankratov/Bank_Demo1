package bank.demo.dto.scanner.ui;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class UIExit implements UIAction {

    @Override
    public void execute() {
        System.out.println("Bye-bye");
        System.exit(0);//выход с приложения
    }

    @Override
    public String getActionName() {
        return "Exit";
    }
}
