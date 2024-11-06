package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.tm.StudentTm;

import java.util.List;

public class StudentFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> clmAddress;

    @FXML
    private TableColumn<?, ?> clmContact;

    @FXML
    private TableColumn<?, ?> clmEmail;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPayed;

    @FXML
    private TableView<StudentTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPayed;

    StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.STUDENT);

    public void initialize() {
        setTable();
        setCellValueFactory();
        selectTableRow();
        generateCustomerId();
    }

    void clearTextFields() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtContact.clear();
        txtPayed.clear();
    }

    private String generateCustomerId() {
        try {
            String currentId = studentBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("S00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "S00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("S001");
                return "S001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void selectTableRow() {
        tblCustomer.setOnMouseClicked(event -> {
            int focusedIndex = tblCustomer.getFocusModel().getFocusedIndex();
            StudentTm studentTm = (StudentTm) tblCustomer.getItems().get(focusedIndex);
            txtId.setText(studentTm.getId());
            txtName.setText(studentTm.getName());
            txtAddress.setText(studentTm.getAddress());
            txtEmail.setText(studentTm.getEmail());
            txtContact.setText(String.valueOf(studentTm.getTel()));
            txtPayed.setText(String.valueOf(studentTm.getPayed()));
        });
    }

    private void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
        clmPayed.setCellValueFactory(new PropertyValueFactory<>("payed"));
    }

    private void setTable() {
        ObservableList<StudentTm> studentTms = FXCollections.observableArrayList();
        List<StudentDTO> all = studentBO.getAll();
        for (StudentDTO customerDto : all) {
            StudentTm customerTm = new StudentTm(customerDto.getId(), customerDto.getName(), customerDto.getAddress(), customerDto.getTel(), customerDto.getEmail(), customerDto.getPayed());
            studentTms.add(customerTm);
        }
        tblCustomer.setItems(studentTms);
    }


    @FXML
    void txtAddressOnAction(ActionEvent event) {

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
    void txtPayedOnAction(ActionEvent event) {

    }




    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isSaved = studentBO.save(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(),Double.parseDouble(txtPayed.getText())));
        if (isSaved){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Customer save successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer save unsuccessfully").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isUpdated = studentBO.update(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(),Double.parseDouble(txtPayed.getText())));
        if (isUpdated){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Customer update successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer update unsuccessfully").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        boolean isDeleted = studentBO.delete(new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText(), Double.parseDouble(txtPayed.getText())));
        if (isDeleted) {
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Customer delete unsuccessful").show();
        }
    }
}
