package lk.ijse.dep13.jdbc;

import java.sql.*;

public class Demo2 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try(Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/dep13_auth_app",
                "postgres", "psql");
        PreparedStatement stm = connection
                .prepareStatement("SELECT * FROM \"user\" WHERE username = ? AND password = ?")){

            String username = "admin' OR TRUE --";
            String password = "abc";

            stm.setObject(1, username);
            stm.setObject(2, password);

            ResultSet rst = stm.executeQuery();
            System.out.println(rst.next() ? "Logged in": "Invalid login credentials");

            stm.setString(1, "lahiru");
            stm.setString(2, "lahiru123");
            rst = stm.executeQuery();

            System.out.println(rst.next() ? "Logged in": "Invalid login credentials");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
