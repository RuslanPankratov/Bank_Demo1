package bank.demo.dto.bd;

import bank.demo.dto.dto.*;
import bank.demo.dto.enum_class.TypeOfBenefits;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class DatabaseLoading implements BDInterface {

    private DB connection = new DB();

    private ListBankAccount listBankAccount = new ListBankAccount();
    private List<Users1> userList = new ArrayList<>();
    private List<CreditCard> creditCardList = new ArrayList<>();
    private List<Credit> creditList = new ArrayList<>();
    private List<Insurance> insuranceList = new ArrayList<>();

    @Override
    public void action() {
        updateBD();
    }

    public void updateBD() {
        updateUser();
        updateCreditCard();
        updateCredit();
        updateInsurance();
        addingBankAccountsToList();
        for (int i = 0; i < listBankAccount.getBankAccountList().size(); i++) {
            System.out.println(listBankAccount.getBankAccountList().get(i).getClientId());
        }

    }

    void updateUser() {
        try (Statement statement = connection.getConnection().createStatement()) {//здесь он подключается к базк данных

            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.user;");
            while (resultSet.next()) { //этот некст будет проходиться по всей таблице
                TypeOfBenefits typeOfBenefits = choiceOfStatus(resultSet.getString("typeOfBenefits"));// так же можно по имени колонки обращаться
//               User user = new User(resultSet.getString(2), resultSet.getString(3),//эти цифры
//                       // говорят о том, с какого столбца мы что берём, отсчёт идёт с 1
                Users1 user = new Users1(resultSet.getString("firstName"), resultSet.getString("lastName"),//эти цифры
                        // говорят о том, с какого столбца мы что берём, отсчёт идёт с 1
                        resultSet.getInt("age"), typeOfBenefits);
                user.setIdUser(resultSet.getInt("iduser"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void updateCreditCard() {
        try (Statement statement = connection.getConnection().createStatement()) {//здесь он подключается к базк данных
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.creditcard;");
            while (resultSet.next()) { //этот некст будет проходиться по всей таблице
                CreditCard creditCard = new CreditCard(resultSet.getBoolean("blocked"), resultSet.getString("login")
                        , resultSet.getString("password"), resultSet.getDouble("invoiceAmount")
                        , resultSet.getDouble("withdrawalLimit"));
                creditCard.setIdCreditCard(resultSet.getInt("idCreditCard"));
                creditCardList.add(creditCard);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void updateCredit() {
        try (
                Statement statement = connection.getConnection().createStatement()) {//здесь он подключается к базк данных
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.credit;");

            while (resultSet.next()) { //этот некст будет проходиться по всей таблице
                Credit credit = new Credit();
                credit.setHowMuchToPay(resultSet.getDouble("howMuchToPay"));
                credit.setPercentRate(resultSet.getDouble("percentRate"));
                credit.setPaid(resultSet.getDouble("paid"));
                credit.setTheTotalAmountYouPay(resultSet.getDouble("theTotalAmountYouPay"));
                credit.setCountMonthsToPay(resultSet.getDouble("countMonthsToPay"));
                credit.setBankProfit(resultSet.getDouble("bankProfit"));
                credit.setHowMuchIsTheLoan(resultSet.getDouble("howMuchIsTheLoan"));
                credit.setPaymentPerMonth(resultSet.getDouble("paymentPerMonth"));
                credit.setIdCredit(resultSet.getInt("idCredit"));

                creditList.add(credit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void updateInsurance() {
        try (
                Statement statement = connection.getConnection().createStatement()) {//здесь он подключается к базк данных
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.insurance;");

            while (resultSet.next()) {
                Insurance insurance = new Insurance(resultSet.getDouble("sumInsured"));
                insurance.setInsurancePaid(resultSet.getDouble("insurancePaid"));
                insurance.setIdInsurance(resultSet.getInt("idInsurance"));

                insuranceList.add(insurance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void addingBankAccountsToList() {

        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                BankAccount bankAccount = new BankAccount(userList.get(i), creditCardList.get(i));
                bankAccount.setClientId(userList.get(i).getIdUser());
                bankAccount.setInsurance(insuranceList.get(i));
                bankAccount.setCredit(creditList.get(i));
                listBankAccount.getBankAccountList().add(bankAccount);
            }
        }
    }

    private TypeOfBenefits choiceOfStatus(String type) {
        if (type.equalsIgnoreCase("2")) {
            return TypeOfBenefits.DISABILITY_ONE_TWO;
        } else if (type.equalsIgnoreCase("3")) {
            return TypeOfBenefits.DISABILITY_THREE_FOUR;
        } else if (type.equalsIgnoreCase("4")) {
            return TypeOfBenefits.THE_LARGE_FAMILY;
        } else if (type.equalsIgnoreCase("5")) {
            return TypeOfBenefits.PENSIONER;
        } else if (type.equalsIgnoreCase("6")) {
            return TypeOfBenefits.VETERAN;
        }
        return TypeOfBenefits.NO_BENEFITS;
    }


}
