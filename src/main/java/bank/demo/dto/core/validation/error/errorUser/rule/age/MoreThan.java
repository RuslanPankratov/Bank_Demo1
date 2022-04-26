package bank.demo.dto.core.validation.error.errorUser.rule.age;

import bank.demo.dto.core.validation.error.errorUser.rule.RuleException;
import org.springframework.stereotype.Component;

@Component
public class MoreThan implements RuleAge{
    @Override
    public void rule(int age) throws RuntimeException {
        if (age < 16){
            throw new RuleException(nameRule());
        }
    }
    @Override
    public String nameRule() {
        return "Need more than 15 years";
    }
}
