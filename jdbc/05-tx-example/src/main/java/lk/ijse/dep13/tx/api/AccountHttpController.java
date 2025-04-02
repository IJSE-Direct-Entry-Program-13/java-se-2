package lk.ijse.dep13.tx.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.sql.*;
import java.time.Duration;

@RestController
@RequestMapping("/accounts")
public class AccountHttpController {

    @PatchMapping("/{accountNumber}")
    public String updateAccount(@PathVariable String accountNumber, BigDecimal amount) throws SQLException, InterruptedException {
        try(Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_tx",
                        "root", "mysql");
            PreparedStatement stm1 = connection
                    .prepareStatement("SELECT balance FROM account WHERE account_number = ?");
            PreparedStatement stm2 = connection
                    .prepareStatement("UPDATE account SET balance = ? WHERE account_number = ?");
//        PreparedStatement stm2 = connection
//                .prepareStatement("UPDATE account SET balance = balance - ? WHERE account_number = ?")
        ){
            connection.setAutoCommit(false);
            try {
                stm1.setString(1, accountNumber);
                ResultSet rst = stm1.executeQuery();
                if (!rst.next()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                BigDecimal currentBalance = rst.getBigDecimal("balance");
                Thread.sleep(Duration.ofSeconds(15));
                stm2.setString(2, accountNumber);
                stm2.setBigDecimal(1, currentBalance.subtract(amount));
//                stm2.setBigDecimal(1, amount);
                stm2.executeUpdate();
                connection.commit();
                return "Account Updated Successfully";
            }catch (Throwable t) {
                connection.rollback();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }finally {
                connection.setAutoCommit(true);
            }
        }
    }
}
