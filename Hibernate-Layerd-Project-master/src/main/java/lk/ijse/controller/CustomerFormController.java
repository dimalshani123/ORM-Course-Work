package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.tm.CustomerTm;

import java.util.List;

public class CustomerFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtEmail;
    public TextField txtContact;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView<CustomerTm>tblCustomer;
    public TableColumn<?,?> clmId;
    public TableColumn<?,?> clmName;
    public TableColumn<?,?> clmAddress;
    public TableColumn<?,?> clmEmail;
    public TableColumn<?,?> clmContact;
    CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.CUSTOMER);

    public void initialize(){
        setTable();
        setCellValueFactory();
        selectTableRow();
        generateCustomerId();
    }
    void clearTextFields(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtContact.clear();
    }
    private String generateCustomerId() {
        try {
            String currentId = customerBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("C00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "C00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("C001");
                return "C001";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private void selectTableRow() {
        tblCustomer.setOnMouseClicked(event -> {
            int focusedIndex = tblCustomer.getFocusModel().getFocusedIndex();
            CustomerTm customerTm = tblCustomer.getItems().get(focusedIndex);
            txtId.setText(customerTm.getId());
            txtName.setText(customerTm.getName());
            txtAddress.setText(customerTm.getAddress());
            txtEmail.setText(customerTm.getEmail());
            txtContact.setText(String.valueOf(customerTm.getTel()));
        });
    }

    private void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void setTable() {
        ObservableList<CustomerTm> customerTms = FXCollections.observableArrayList();
        List<CustomerDto> all = customerBO.getAll();
        for (CustomerDto customerDto : all){
            CustomerTm customerTm = new CustomerTm(customerDto.getId(), customerDto.getName(), customerDto.getAddress(), customerDto.getTel(), customerDto.getEmail());
            customerTms.add(customerTm);
        }
        tblCustomer.setItems(customerTms);
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        txtAddress.requestFocus();
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void txtEmailOnAction(ActionEvent actionEvent) {
        txtContact.requestFocus();
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
        btnSaveOnAction(actionEvent);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isSaved = customerBO.save(new CustomerDto(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText()));
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
        boolean isUpdated = customerBO.update(new CustomerDto(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText()));
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
        boolean isDeleted = customerBO.delete(new CustomerDto(txtId.getText(), txtName.getText(), txtAddress.getText(), Integer.parseInt(txtContact.getText()), txtEmail.getText()));
        if (isDeleted){
            clearTextFields();
            setTable();
            setCellValueFactory();
            tblCustomer.refresh();
            txtId.setText(generateCustomerId());
            new Alert(Alert.AlertType.CONFIRMATION,"Customer delete successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR,"Customer delete unsuccessfully").show();
        }
    }
}
