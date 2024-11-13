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
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginCheckFormController {

    @FXML
    private Button btnEnter;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPossition;

    @FXML
    void btnEnterOnAction(ActionEvent event) {
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String position = txtPossition.getText();

        // Hash the entered password, skip hashing if the position is "admin"
        String hashedPassword = position.equalsIgnoreCase("admin") ? password : hashPassword(password);

        // Validate login with hashed password
        User user = validateLogin(name, hashedPassword, position);

        if (user != null) {
            try {
                String fxmlFile = position.equalsIgnoreCase("admin") ? "/view/dashboardForm.fxml" : "/view/dashboardForm2.fxml";

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = fxmlLoader.load();

                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root));
                stage.show();

                ((Stage) btnEnter.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username, password, or position.");
            alert.showAndWait();
        }
    }

    private User validateLogin(String name, String hashedPassword, String position) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE name = :name AND password = :password AND position = :position", User.class);
            query.setParameter("name", name);
            query.setParameter("password", hashedPassword);
            query.setParameter("position", position);

            return query.uniqueResult();
        }
    }

    // Utility method to hash password using SHA-256 (matches UserFormController hashing)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
