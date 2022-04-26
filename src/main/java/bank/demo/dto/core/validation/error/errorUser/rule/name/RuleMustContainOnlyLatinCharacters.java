package bank.demo.dto.core.validation.error.errorUser.rule.name;

import bank.demo.dto.core.validation.error.errorUser.rule.RuleException;
import org.springframework.stereotype.Component;

@Component
public class RuleMustContainOnlyLatinCharacters implements RuleFirstNameAndLastName {

    @Override
    public void rule(String firstNameOrLastName) {
        if (firstNameOrLastName.matches("[0-9]*")) {
                throw new RuleException(nameRule());
        }
//        if (firstNameOrLastName.equalsIgnoreCase("prv")){
//            throw new RuleException(nameRule());
//        }
    }

    @Override
    public String nameRule() {
        return "Rule Must Contain Only Latin Characters";
    }
}
