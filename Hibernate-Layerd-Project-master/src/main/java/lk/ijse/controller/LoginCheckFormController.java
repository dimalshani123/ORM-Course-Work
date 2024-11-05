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

        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/dashboardForm.fxml"));
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
