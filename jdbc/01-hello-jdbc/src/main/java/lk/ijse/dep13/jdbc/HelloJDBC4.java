package lk.ijse.dep13.jdbc;

import java.sql.*;

public class HelloJDBC4 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_jdbc",
                "root", "mysql");

        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM customer");
        while (rst.next()){
            int id = rst.getInt(1);
            String name = rst.getString("name");
            String address = rst.getString(3);
            System.out.println(id + ", " + name + ", " + address);
        }

        connection.close();
    }
}
