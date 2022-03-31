package bank.demo.dto.scanner.ui;

import org.springframework.stereotype.Component;


public interface UIAction {

    void execute();

    String getActionName();

}