package bank.demo.dto.scanner.ui;

import bank.demo.dto.bd.table.CreateUser;
import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.helper.rule.RuleFirstNameAndLastName;
import bank.demo.dto.services.BankAccountCreation;
import org.hibernate.SessionFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(2)// делает последовательность в выводе при спринге, когда используется лист или сет
@Component
public class UICreate implements UIAction {
    private List<RuleFirstNameAndLastName> ruleFirstNameAndLastName;
    private ListBankAccount listBankAccount;
    private SessionFactory sessionFactory;

  //  @Autowired  я могу писать без конструктора, если напишу так, он автоматически подтянет изменения
//    private List<RuleFirstNameAndLastName> ruleFirstNameAndLastName;
  //  @Autowired
//    private ListBankAccount listBankAccount;
    //когда есть конструктор, он автоматически пишет autowride в конструкторе


    public UICreate(List<RuleFirstNameAndLastName> ruleFirstNameAndLastName, ListBankAccount listBankAccount, SessionFactory sessionFactory) {
        this.ruleFirstNameAndLastName = ruleFirstNameAndLastName;
        this.listBankAccount = listBankAccount;

        this.sessionFactory = sessionFactory;
    }

    @Override
    public void execute() {

        BankAccountCreation bankAccountCreation = new BankAccountCreation(ruleFirstNameAndLastName,sessionFactory);
        bankAccountCreation.createBankAccount(listBankAccount);
    }

    @Override
    public String getActionName() {
        return "Create bank account";
    }

    public List<RuleFirstNameAndLastName> getRuleFirstNameAndLastName() {
        return ruleFirstNameAndLastName;
    }

    public void setRuleFirstNameAndLastName(List<RuleFirstNameAndLastName> ruleFirstNameAndLastName) {
        this.ruleFirstNameAndLastName = ruleFirstNameAndLastName;
    }

    public ListBankAccount getListBankAccount() {
        return listBankAccount;
    }

    public void setListBankAccount(ListBankAccount listBankAccount) {
        this.listBankAccount = listBankAccount;
    }
}
