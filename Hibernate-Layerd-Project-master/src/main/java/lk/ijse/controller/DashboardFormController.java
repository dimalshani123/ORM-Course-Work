package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnItem;

    @FXML
    private Pane rootNode;

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        rootNode.getChildren().clear();
        try {
            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/customerForm.fxml")));
        } catch (IOException e) {}
    }

    @FXML
    void btnItemOnAction(ActionEvent event) {
        rootNode.getChildren().clear();
        try {
            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/programForm.fxml")));
        } catch (IOException e) {}

    }


}
