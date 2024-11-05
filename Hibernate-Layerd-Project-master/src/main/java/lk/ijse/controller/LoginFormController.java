package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegister;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/loginCheckForm.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage (window)
            Stage stage = new Stage();
            stage.setTitle("Checking Form");
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }

    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/userForm.fxml"));
            Parent root = fxmlLoader.load();

            // Create a new stage (window)
            Stage stage = new Stage();
            stage.setTitle("User Form");
            stage.setScene(new Scene(root));

            // Show the new stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Log or handle the exception appropriately
        }
    }

}
