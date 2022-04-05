package bank.demo.dto.services.cardEnter;

import bank.demo.dto.bd.DB;
import bank.demo.dto.dto.ListBankAccount;
import bank.demo.dto.enum_class.TypeOfBenefits;
import bank.demo.dto.scanner.ScannerCardEntry;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;

@Order(4)
@Component
public class EditingCardEntryIMPL implements CardEntryIMPL {

    private DB connection = new DB();

    @Override
    public void menu(ListBankAccount listBankAccount, int i) {
        ScannerCardEntry scannerCardEntry = new ScannerCardEntry();
        String result = scannerCardEntry.methodResult();
        if (result.equals("1")) {
            editing(listBankAccount, i);
        } else if (result.equals("2")) {
            statusEditing(listBankAccount, i);
        }
    }

    @Override
    public String getActionName() {
        return "Editing";
    }


    private void editing(ListBankAccount listBankAccount, int i) {
        ScannerCardEntry scannerCardEntry = new ScannerCardEntry();

        String firstName = scannerCardEntry.methodFirstName();
        String lastName = scannerCardEntry.methodLastName();
        listBankAccount.getBankAccountList().get(i).getUser().setFirstName(firstName);
        listBankAccount.getBankAccountList().get(i).getUser().setLastName(lastName);
        System.out.println("now your name " + firstName + " and your last name " + lastName);

        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("update `bank`.`user` set `firstName`='" + firstName
                    + "',`lastName`='" + lastName + "' where`iduser`="
                    + listBankAccount.getBankAccountList().get(i).getUser().getIdUser() + "; ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void statusEditing(ListBankAccount listBankAccount, int i) {

        ScannerCardEntry scannerCardEntry = new ScannerCardEntry();
        String resultType = scannerCardEntry.methodResultType();

        if (resultType.equals("1")) {
            listBankAccount.getBankAccountList().get(i).getUser().setTypeOfBenefits(TypeOfBenefits.NO_BENEFITS);
        } else if (resultType.equals("2")) {
            listBankAccount.getBankAccountList().get(i).getUser().setTypeOfBenefits(TypeOfBenefits.DISABILITY_ONE_TWO);
        } else if (resultType.equals("3")) {
            listBankAccount.getBankAccountList().get(i).getUser().setTypeOfBenefits(TypeOfBenefits.DISABILITY_THREE_FOUR);
        } else if (resultType.equals("4")) {
            listBankAccount.getBankAccountList().get(i).getUser().setTypeOfBenefits(TypeOfBenefits.THE_LARGE_FAMILY);
        } else if (resultType.equals("5")) {
            listBankAccount.getBankAccountList().get(i).getUser().setTypeOfBenefits(TypeOfBenefits.PENSIONER);
        } else if (resultType.equals("6")) {
            listBankAccount.getBankAccountList().get(i).getUser().setTypeOfBenefits(TypeOfBenefits.VETERAN);
        }
        System.out.println("Your status: " + listBankAccount.getBankAccountList().get(i).getUser().getTypeOfBenefits());
        try {
            Statement statement = connection.getConnection().createStatement();
            statement.executeUpdate("update `bank`.`user` set `typeOfBenefits`='" + resultType + "' where`iduser`="
                    + listBankAccount.getBankAccountList().get(i).getUser().getIdUser() + ";  ");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
