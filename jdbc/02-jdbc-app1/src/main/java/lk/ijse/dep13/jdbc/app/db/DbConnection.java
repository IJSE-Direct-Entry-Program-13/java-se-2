package lk.ijse.dep13.jdbc.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final DbConnection INSTANCE = new DbConnection();
    private final Connection CONNECTION;
    private DbConnection() {
        try {
            CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep13_jdbc",
                    "root", "mysql");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static DbConnection getInstance() {
        return INSTANCE;
    }
    public Connection getConnection() {
        return CONNECTION;
    }
}
