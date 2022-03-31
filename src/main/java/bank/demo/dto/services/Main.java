package bank.demo.dto.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String userName = "root";
        String password = "root";
        String connectionUrl = "jdbc:mysql://localhost:3306/bank";
        try(Connection connection = DriverManager.getConnection(connectionUrl, userName, password); //здесь он подключается к базк данных
            Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `bank`.`name` (\n" +
                    "  `idname` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `namecol` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`idname`, `namecol`));\n");
        }
    }
}
