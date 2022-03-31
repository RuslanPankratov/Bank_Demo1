package bank.demo.dto.bd;

import bank.demo.dto.dto.Insurance;
import bank.demo.dto.dto.User;
import bank.demo.dto.enum_class.TypeOfBenefits;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Prov {
    private DB connection = new DB();
    //SELECT MAX(iduser) FROM bank.user;
      List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        Prov prov = new Prov();
        prov.create();
    }

      void ve (){
          var keyHolder = new GeneratedKeyHolder();

          int re = keyHolder.getKey().intValue();
          System.out.println(re);
      }


    void create(){

        try (Statement statement = connection.getConnection().createStatement()) {//здесь он подключается к базк данных

            ResultSet resultSet = statement.executeQuery("SELECT * FROM bank.user where `iduser`=2;");
            if (resultSet.next()){
                String res = resultSet.getString(1);
                System.out.println(res);
            }
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM bank.user ORDER BY `iduser` DESC LIMIT 1;");
            if (resultSet1.next()){
                String res = resultSet1.getString(1);
                System.out.println(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
