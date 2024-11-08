//package lk.ijse.controller;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//
//import java.io.IOException;
//
//public class DashboardFormController {
//
//    @FXML
//    private Button btnCustomer;
//
//    @FXML
//    private Button btnPayment;
//
//
//    @FXML
//    private Button btnProgram;
//
//    @FXML
//    private Button btnUser;
//
//    @FXML
//    private Pane rootNode;
//
//    @FXML
//    void btnCustomerOnAction(ActionEvent event) {
//        rootNode.getChildren().clear();
//        try {
//            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/studentForm.fxml")));
//        } catch (IOException e) {
//        }
//    }
//
//    @FXML
//    void btnPaymentOnAction(ActionEvent event) {
//        rootNode.getChildren().clear();
//        try {
//            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/paymentForm.fxml")));
//        } catch (IOException e) {
//        }
//    }
//
//
//    @FXML
//    void btnProgramOnAction(ActionEvent event) {
//        rootNode.getChildren().clear();
//        try {
//            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/programForm.fxml")));
//        } catch (IOException e) {}
//
//    }
//
//    @FXML
//    void btnUserOnAction(ActionEvent event) {
//        rootNode.getChildren().clear();
//        try {
//            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/userForm.fxml")));
//        } catch (IOException e) {}
//
//    }
//
//
//}

package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private Button btnCustomer;

    @FXML
    private Button btnPayment;

    @FXML
    private Button btnProgram;

    @FXML
    private Button btnUser;

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
    void btnPaymentOnAction(ActionEvent event) {
        rootNode.getChildren().clear();
        try {
            rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/paymentForm.fxml")));
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

    @FXML
    void btnUserOnAction(ActionEvent event) {
        rootNode.getChildren().clear();
        try {
           rootNode.getChildren().add(FXMLLoader.load(getClass().getResource("/view/userForm.fxml")));
        } catch (IOException e) {}

    }
    }



