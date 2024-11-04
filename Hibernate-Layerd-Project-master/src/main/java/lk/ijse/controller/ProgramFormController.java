package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.entity.tm.ProgramTm;

import java.util.List;

public class ProgramFormController {

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmPrice;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableView<ProgramTm> tblItem;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtDuration;

    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.ITEM);

    public void initialize() {
        setItemTable();
        setCellValueFactory();
        tableSelection();
        generateItemId();
    }

    void clearFields() {
        txtId.clear();
        txtItem.clear();
        txtPrice.clear();
        txtDuration.clear();
    }

    private String generateItemId() {
        try {
            String currentId = itemBO.getCurrentId();
            if (currentId != null) {
                String[] split = currentId.split("P00");
                int idNum = Integer.parseInt(split[1]);
                String availableId = "P00" + ++idNum;
                txtId.setText(availableId);
                return availableId;
            } else {
                txtId.setText("P001");
                return "P001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void tableSelection() {
        tblItem.setOnMouseClicked(event -> {
            int index = tblItem.getSelectionModel().getSelectedIndex();
            txtId.setText(String.valueOf(clmId.getCellData(index)));
            txtItem.setText(String.valueOf(clmName.getCellData(index)));
            txtPrice.setText(String.valueOf(clmPrice.getCellData(index)));
            txtDuration.setText(String.valueOf(clmDuration.getCellData(index)));
        });
    }

    private void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    private void setItemTable() {
        ObservableList<ProgramTm> obList = FXCollections.observableArrayList();
        List<ProgramDTO> items = itemBO.getAll();
        for (ProgramDTO itemDto : items) {
            ProgramTm itemTm = new ProgramTm(
                    itemDto.getItemCode(),
                    itemDto.getName(),
                    itemDto.getPrice(),
                    itemDto.getDuration()
            );
            obList.add(itemTm);
        }
        tblItem.setItems(obList);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = itemBO.delete(new ProgramDTO(txtId.getText(), txtItem.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isDeleted) {
            clearFields();
            setItemTable();
            setCellValueFactory();
            tblItem.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Item deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item deleted unsuccessfully");
        }
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isSaved = itemBO.save(new ProgramDTO(txtId.getText(), txtItem.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isSaved) {
            clearFields();
            txtId.setText(generateItemId());
            setItemTable();
            setCellValueFactory();
            tblItem.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Item saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item saved unsuccessfully");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isUpdated = itemBO.update(new ProgramDTO(txtId.getText(), txtItem.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isUpdated) {
            clearFields();
            setItemTable();
            setCellValueFactory();
            tblItem.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Item updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item updated unsuccessfully");
        }
    }


    @FXML
    void txtDurationOnAction(ActionEvent event) {
            txtId.requestFocus();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtItem.requestFocus();
    }

    @FXML
    void txtItemOnAction(ActionEvent event) {
        txtPrice.requestFocus();
    }

    @FXML
    void txtPriceOnAction(ActionEvent event) {
        btnSaveOnAction(event);
    }
}
