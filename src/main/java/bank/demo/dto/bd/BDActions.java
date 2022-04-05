package bank.demo.dto.bd;

import bank.demo.dto.dto.ListBankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BDActions {
    @Autowired
    private List<BDInterface> bdActions;
    @Autowired
    private ListBankAccount listBankAccount = new ListBankAccount();


    public void action() {
        for (int i = 0; i < bdActions.size(); i++) {
            bdActions.get(i).action();
        }
    }

    public List<BDInterface> getBdActions() {
        return bdActions;
    }

    public void setBdActions(List<BDInterface> bdActions) {
        this.bdActions = bdActions;
    }

    public ListBankAccount getListBankAccount() {
        return listBankAccount;
    }

    public void setListBankAccount(ListBankAccount listBankAccount) {
        this.listBankAccount = listBankAccount;
    }
}
