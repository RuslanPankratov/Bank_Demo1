package bank.demo.dto.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private String userName = "root";
    private String password = "root";
    private String connectionUrl = "jdbc:mysql://localhost:3306/bank";

    private Connection connection;

    public DB(){
        try {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
