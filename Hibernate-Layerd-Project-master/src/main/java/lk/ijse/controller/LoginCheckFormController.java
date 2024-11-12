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

        User user = validateLogin(name, password, position);

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

    private User validateLogin(String name, String password, String position) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE name = :name AND password = :password AND position = :position", User.class);
            query.setParameter("name", name);
            query.setParameter("password", password);
            query.setParameter("position", position);

            return query.uniqueResult();
        }
    }
}
