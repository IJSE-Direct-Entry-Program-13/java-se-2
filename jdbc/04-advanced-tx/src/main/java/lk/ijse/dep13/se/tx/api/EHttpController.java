package lk.ijse.dep13.se.tx.api;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RestController
@RequestMapping("/e")
public class EHttpController {

    @PatchMapping
    public String changeData(String id, String name, String address) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_tx",
                        "root", "mysql");
             PreparedStatement stm = connection
                     .prepareStatement("INSERT INTO customer (id, name, address) VALUES (?, ?, ?)")) {
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, address);
            stm.executeUpdate();
            return "Data added successfully";
        }
    }
}

