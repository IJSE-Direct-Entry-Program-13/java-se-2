package lk.ijse.dep13.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJDBC2 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_jdbc",
                "root", "mysql");

        Statement stm = connection.createStatement();
        int affectedRows = stm
                .executeUpdate("UPDATE customer SET address='Panadura' WHERE address='Galle'");
        System.out.println(affectedRows);

        connection.close();
    }
}
