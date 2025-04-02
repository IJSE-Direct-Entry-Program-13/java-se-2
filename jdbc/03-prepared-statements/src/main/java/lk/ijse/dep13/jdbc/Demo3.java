package lk.ijse.dep13.jdbc;

import java.math.BigDecimal;
import java.sql.*;

public class Demo3 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/dep13_auth_app",
                        "postgres", "psql");
             PreparedStatement stm1 = connection
                     .prepareStatement("SELECT balance FROM account WHERE account_no = ?");
             PreparedStatement stm2 = connection
                     .prepareStatement("UPDATE account SET balance = ? WHERE account_no = ?")) {
            // Let's begin a new transaction context
            connection.setAutoCommit(false);
            try {
                stm1.setString(1, "a");
                ResultSet rst = stm1.executeQuery();
                rst.next();
                BigDecimal balanceOfA = rst.getBigDecimal("balance");
                stm1.setString(1, "b");
                rst = stm1.executeQuery();
                rst.next();
                BigDecimal balanceOfB = rst.getBigDecimal("balance");

                System.out.println("Account Balance of A: " + balanceOfA);
                System.out.println("Account Balance of B: " + balanceOfB);

                BigDecimal amountToBeTransferred = new BigDecimal("1000");
                BigDecimal newBalanceOfA = balanceOfA.subtract(amountToBeTransferred);  // Debit
                BigDecimal newBalanceOfB = balanceOfB.add(amountToBeTransferred);       // Credit

                stm2.setBigDecimal(1, newBalanceOfA);
                stm2.setString(2, "a");
                stm2.executeUpdate();

                //if (true) throw new Error("Something went wrong");

                stm2.setBigDecimal(1, newBalanceOfB);
                stm2.setString(2, "b");
                stm2.executeUpdate();
                System.out.println("Amount Transferred: " + amountToBeTransferred + " successfully from account A to B");

                System.out.printf("Account Balance of A: %.2f - %.2f = %.2f %n",
                        balanceOfA, amountToBeTransferred, newBalanceOfA);
                System.out.printf("Account Balance of A: %.2f + %.2f = %.2f %n",
                        balanceOfB, amountToBeTransferred, newBalanceOfB);

                // Let's persist the transaction context
                connection.commit();
            }catch (Throwable throwable) {
                // Let's clear the buffer
                connection.rollback();
                System.out.println("Something went wrong, transaction failed");
            }finally {
                // Let's end the transaction
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
