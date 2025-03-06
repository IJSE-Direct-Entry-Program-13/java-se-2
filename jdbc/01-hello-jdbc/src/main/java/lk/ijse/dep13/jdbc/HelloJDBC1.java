package lk.ijse.dep13.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJDBC1 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_jdbc",
                "root", "mysql");

        Statement stm = connection.createStatement();
        for (int i = 0; i < 100; i++) {
            int affectedRows = stm
                    .executeUpdate("INSERT INTO customer (name, address) VALUES ('Kasun', 'Galle')");
            System.out.println(affectedRows);
        }

        connection.close();
    }
}
