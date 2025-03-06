package lk.ijse.dep13.jdbc.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnection {
    private static final DbConnection INSTANCE = new DbConnection();
    private final Connection CONNECTION;

    private DbConnection() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("/application.properties"));

            String host = properties.getProperty("app.host");
            String port = properties.getProperty("app.port");
            String databaseName = properties.getProperty("app.database-name");
            String username = properties.getProperty("app.user");
            String password = properties.getProperty("app.password");

            CONNECTION = DriverManager.getConnection("jdbc:mysql://%s:%s/%s"
                    .formatted(host, port, databaseName), username, password);
        } catch (Exception e) {
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
