package lk.ijse.dep13.se.tx.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.time.Duration;

@RestController
@RequestMapping("/c")
public class CHttpController {

    @GetMapping
    public String getData(String id) throws SQLException, InterruptedException {
        try(Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_tx",
                        "root", "mysql");
            PreparedStatement stm = connection
                    .prepareStatement("SELECT * FROM customer WHERE id = ?")){
            connection.setAutoCommit(false);
            try {
                stm.setString(1, id);
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String result = "Name: " + name + ", Address: " + address;
                    Thread.sleep(Duration.ofSeconds(20));
                    stm.setString(1, id);
                    rs = stm.executeQuery();
                    rs.next();
                    name = rs.getString("name");
                    address = rs.getString("address");
                    return (result + "<br>" + "Name: " + name + ", Address: " + address);
                } else {
                    return "No such customer";
                }
            }finally {
                connection.setAutoCommit(true);
            }
        }
    }
}

