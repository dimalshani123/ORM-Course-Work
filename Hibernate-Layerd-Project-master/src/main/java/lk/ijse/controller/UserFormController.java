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
            UserTm userTm = new UserTm(userDTO.getId(), userDTO.getName(), userDTO.getPossition(), userDTO.getTel(), userDTO.getEmail(),userDTO.getPassword());
            userTms.add(userTm);
        }
        tblUser.setItems(userTms);
    }



    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtPossitionOnAction(ActionEvent event) {

    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isSaved = userBo.save(new UserDTO(txtId.getText(), txtName.getText(), txtPossition.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(),txtPassword.getText()));
        if (isSaved){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION,"User save successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"User save unsuccessfully").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isUpdated = userBo.update(new UserDTO(txtId.getText(), txtName.getText(), txtPossition.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(),txtPassword.getText()));
        if (isUpdated){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION,"User update successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"User update unsuccessfully").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean isDeleted = userBo.delete(new UserDTO(txtId.getText(), txtName.getText(), txtPossition.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(),txtPassword.getText()));
        if (isDeleted){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblUser.refresh();
            txtId.setText(generateUserId());
            new Alert(Alert.AlertType.CONFIRMATION,"User delete successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"User delete unsuccessfully").show();
        }
    }
}


