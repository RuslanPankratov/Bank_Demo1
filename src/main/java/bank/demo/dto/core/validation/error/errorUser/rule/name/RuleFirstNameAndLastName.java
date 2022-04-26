package bank.demo.dto.core.validation.error.errorUser.rule.name;

import bank.demo.dto.core.validation.error.errorUser.rule.RuleException;

public interface RuleFirstNameAndLastName {
//лучше использовать эти правила, но только не просто на имя
    // попробуй через мапу использовать все эроры разом, или только имя и фамилию
    //
    void rule(String firstNameOrLastName) throws RuleException;

    String nameRule();
}
