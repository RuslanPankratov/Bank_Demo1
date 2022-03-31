package bank.demo.dto.helper.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class RuleException extends RuntimeException {


    public RuleException(String message) {
        super(message);
    }

}
