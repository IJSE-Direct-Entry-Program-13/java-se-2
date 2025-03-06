package lk.ijse.dep13.jdbc.app.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/dep13_jdbc", "root", "mysql");
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM customer");
        while (rst.next()) {
            int id = rst.getInt("id");
            String name = rst.getString("name");
            String address = rst.getString("address");
            System.out.println(id + " " + name + " " + address);
        }
        connection.close();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
    }
}
