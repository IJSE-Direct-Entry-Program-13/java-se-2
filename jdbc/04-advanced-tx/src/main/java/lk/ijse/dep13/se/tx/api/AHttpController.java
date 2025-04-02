package lk.ijse.dep13.se.tx.api;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;

@RestController
@RequestMapping("/a")
public class AHttpController {

    @PatchMapping
    public String changeData(String id, String name, String address) throws SQLException {
        try(Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_tx",
                        "root", "mysql");
        PreparedStatement stm = connection
                .prepareStatement("UPDATE customer SET name = ?, address = ? WHERE id = ?")){
            connection.setAutoCommit(false);
            try {
                stm.setString(1, name);
                stm.setString(2, address);
                stm.setString(3, id);
                stm.executeUpdate();
                Thread.sleep(Duration.ofSeconds(20));
                connection.commit();
                return "Data updated successfully";
            }catch (Throwable ex) {
                connection.rollback();
                return "Failed to update data";
            }finally {
                connection.setAutoCommit(true);
            }
        }
    }
}

