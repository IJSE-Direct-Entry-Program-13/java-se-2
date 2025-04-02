package lk.ijse.dep13.se.tx.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.time.Duration;

@RestController
@RequestMapping("/f")
public class FHttpController {

    @GetMapping
    public String getData(String address) throws SQLException, InterruptedException {
        try(Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_tx",
                        "root", "mysql");
            PreparedStatement stm = connection
                    .prepareStatement("SELECT * FROM customer WHERE address = ?")){
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
//            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            connection.setAutoCommit(false);
            try {
                stm.setString(1, address);
                ResultSet rs = stm.executeQuery();
                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String id = rs.getString("id");
                    sb.append("Id: ").append(id).append(", Name: ").append(name).append(", Address: ")
                            .append(address).append("<br>");
                }
                Thread.sleep(Duration.ofSeconds(20));
                sb.append("<hr>");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String id = rs.getString("id");
                    sb.append("Id: ").append(id).append(", Name: ").append(name).append(", Address: ")
                            .append(address).append("<br>");
                }
                return sb.toString();
            }finally {
                connection.setAutoCommit(true);
            }
        }
    }
}

