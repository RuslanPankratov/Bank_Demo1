package bank.demo.dto.helper.rule;

import org.springframework.stereotype.Component;


public interface RuleFirstNameAndLastName {

    void rule(String firstNameOrLastName);
    String nameRule();
}
