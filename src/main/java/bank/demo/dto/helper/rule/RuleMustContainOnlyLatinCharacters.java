package bank.demo.dto.helper.rule;

import org.springframework.stereotype.Component;

@Component
public class RuleMustContainOnlyLatinCharacters implements RuleFirstNameAndLastName {

    @Override
    public void rule(String firstNameOrLastName) {
        if (firstNameOrLastName.matches("[0-9]")) {
            throw new RuleException(nameRule());
        }
    }

    @Override
    public String nameRule() {
        return "Rule Must Contain Only Latin Characters";
    }
}
