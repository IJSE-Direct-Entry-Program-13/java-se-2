package lk.ijse.dep13.se.tx.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.time.Duration;

@RestController
@RequestMapping("/b")
public class BHttpController {

    @GetMapping
    public String getData(String id) throws SQLException {
        try(Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_tx",
                        "root", "mysql");
            PreparedStatement stm = connection
                    .prepareStatement("SELECT * FROM customer WHERE id = ?")){
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                return "Name: " + name + ", Address: " + address;
            }else{
                return "No such customer";
            }
        }
    }
}

