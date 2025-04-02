package lk.ijse.dep13.jdbc;

import java.sql.*;

public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try(Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/dep13_auth_app",
                "postgres", "psql");
        Statement stm = connection.createStatement()){

            String username = "admin' OR TRUE --";
            String password = "abc";

            ResultSet rst = stm
                    .executeQuery("SELECT * FROM \"user\" WHERE username = '%s' AND password = '%s'"
                    .formatted(username, password));

            System.out.println(rst.next() ? "Logged in": "Invalid login credentials");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
