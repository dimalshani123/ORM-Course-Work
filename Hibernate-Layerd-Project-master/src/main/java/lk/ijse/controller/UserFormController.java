package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.tm.UserTm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmEmail;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPassword;

    @FXML
    private TableColumn<?, ?> clmPossition;

    @FXML
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPossition;

    UserBO userBo = (UserBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.USER);

    public void initialize(){
        setTable();
        setCellValueFactory();
        selectTableRow();
        generateUserId();
    }
    void clearTextFields(){
        txtId.clear();
        txtName.clear();
        txtPossition.clear();
        txtEmail.clear();
        txtContact.clear();
        txtPassword.clear();
    }

    private String generateUserId() {
        try {
            String currentId = userBo.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("U00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "U00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("U001");
                return "U001";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private void selectTableRow() {
        tblUser.setOnMouseClicked(event -> {
            int focusedIndex = tblUser.getFocusModel().getFocusedIndex();
            UserTm userTm = (UserTm) tblUser.getItems().get(focusedIndex);
            txtId.setText(userTm.getId());
            txtName.setText(userTm.getName());
            txtPossition.setText(userTm.getPossition());
            txtEmail.setText(userTm.getEmail());
            txtContact.setText(String.valueOf(userTm.getTel()));
            txtPassword.setText(userTm.getPassword());
        });
    }

    private void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPossition.setCellValueFactory(new PropertyValueFactory<>("possition"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void setTable() {
        ObservableList<UserTm> userTms = FXCollections.observableArrayList();
        List<UserDTO> all = userBo.getAll();
        for (UserDTO userDTO : all){
            UserTm userTm = new UserTm(userDTO.getId(), userDTO.getName(), userDTO.getPossition(), userDTO.getTel(), userDTO.getEmail(), userDTO.getPassword());
            userTms.add(userTm);
        }
        tblUser.setItems(userTms);
    }

    // Utility method to hash password using SHA-256
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

    @FXML
    void txtContactOnAction(ActionEvent event) {
        txtPossition.requestFocus();
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        txtContact.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtPossitionOnAction(ActionEvent event) {
        txtId.requestFocus();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String hashedPassword = hashPassword(txtPassword.getText());
        boolean isSaved = userBo.save(new UserDTO(txtId.getText(), txtName.getText(), txtPossition.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(), hashedPassword));
        if (isSaved){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User save failed").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String hashedPassword = hashPassword(txtPassword.getText());
        boolean isUpdated = userBo.update(new UserDTO(txtId.getText(), txtName.getText(), txtPossition.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(), hashedPassword));
        if (isUpdated){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User update failed").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean isDeleted = userBo.delete(new UserDTO(txtId.getText(), txtName.getText(), txtPossition.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(), txtPassword.getText()));
        if (isDeleted){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION, "User deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User delete failed").show();
        }
    }
}
