package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginCheckFormController {

    @FXML
    private Button btnEnter;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnEnterOnAction(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();


        // Database connection details (replace with your actual values)
        String url = "jdbc:mysql://localhost:3306/kade"; // Replace with your database URL
        String username = "your_username"; // Replace with your database username
        String userId = "your_password"; // Replace with your database password

        Connection connection = null;


        try {
            // Connect to the database
            connection = DriverManager.getConnection(url, username, password);

            // Prepare a statement to check username and password
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1,
                    userName);
            statement.setString(2, password);


            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Login successful
                System.out.println("Login Successful for user: " + userName);

                // You can switch to another scene here
                // ...

            } else {
                // Login failed
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Login Failed");
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database connection errors
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("An error occurred while connecting to the database.");
            alert.showAndWait();

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
