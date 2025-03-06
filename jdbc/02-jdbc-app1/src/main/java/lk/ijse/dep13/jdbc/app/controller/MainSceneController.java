package lk.ijse.dep13.jdbc.app.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dep13.jdbc.app.db.DbConnection;
import lk.ijse.dep13.jdbc.app.dto.Customer;

import java.sql.*;

public class MainSceneController {
    public AnchorPane root;
    public TableView<Customer> tblCustomers;
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSave;
    public Button btnDelete;
    public Button btnNewCustomer;

    public void initialize() throws SQLException {
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        loadAllCustomers();
    }

    private void loadAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM customer");
        while (rst.next()) {
            int id = rst.getInt("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            tblCustomers.getItems().add(new Customer(id, name, address));
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText().trim();
        String address = txtAddress.getText().trim();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            txtName.selectAll();
            return;
        } else if (address.length() < 3) {
            new Alert(Alert.AlertType.ERROR, "Invalid address").show();
            txtAddress.requestFocus();
            txtAddress.selectAll();
            return;
        }

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            stm.executeUpdate("INSERT INTO customer(name, address) VALUES ('%s', '%s')"
                    .formatted(name, address), Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = stm.getGeneratedKeys();
            generatedKeys.next();
            tblCustomers.getItems().add(new Customer(generatedKeys.getInt(1), name, address));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer, try again").show();
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
    }
}
