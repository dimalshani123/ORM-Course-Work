package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardForm2Controller {

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnProgram;

    @FXML
    private Pane rootNode;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        rootNode.getChildren().clear();
        try {
            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/studentForm.fxml")));
        } catch (IOException e) {
        }
    }

    @FXML
    void btnProgramOnAction(ActionEvent event) {
        rootNode.getChildren().clear();
        try {
            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/programForm.fxml")));
        } catch (IOException e) {}
    }

}
