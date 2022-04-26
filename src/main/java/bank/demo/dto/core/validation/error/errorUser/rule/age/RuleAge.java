package bank.demo.dto.core.validation.error.errorUser.rule.age;

public interface RuleAge {

  void rule(int age) throws RuntimeException;
  String nameRule();
}
