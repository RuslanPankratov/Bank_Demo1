package bank.demo.dto.helper.rule;

import org.springframework.stereotype.Component;

@Component//связывает бины в контейнер
public class MoreThanOneCharacter implements RuleFirstNameAndLastName{

    @Override
    public void rule(String firstNameOrLastName) {
        if (firstNameOrLastName.length() < 1 ){
            throw new RuleException(nameRule());
        }
    }

    @Override
    public String nameRule() {
        return "Need more than one character";
    }
}
