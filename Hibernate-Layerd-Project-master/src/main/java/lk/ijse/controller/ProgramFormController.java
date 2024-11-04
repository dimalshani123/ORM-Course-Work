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
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ProgramBO;
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

    ProgramBO programBO = (ProgramBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.PROGRAM);

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

//    private String generateItemId() {
//        try {
//            String currentId = programBO.getCurrentId();
//            if (currentId != null) {
//                String[] split = currentId.split("P00");
//                int idNum = Integer.parseInt(split[1]);
//                String availableId = "P00" + ++idNum;
//                txtId.setText(availableId);
//                return availableId;
//            } else {
//                txtId.setText("P001");
//                return "P001";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    private String generateItemId() {
        try {
            String currentId = programBO.getCurrentId();
            if (currentId != null && currentId.matches("P\\d{3}")) {
                // Extract the numeric part and increment it
                int idNum = Integer.parseInt(currentId.substring(1)); // Removes the 'P' prefix and parses the number
                idNum++; // Increment the number
                String newId = String.format("P%03d", idNum); // Format the number as Pxxx (e.g., P001, P002)
                txtId.setText(newId);
                return newId;
            } else {
                // If currentId is null or format is not recognized, start from P001
                txtId.setText("P001");
                return "P001";
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error generating ID").show();
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
        clmId.setCellValueFactory(new PropertyValueFactory<>("programCode"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    private void setItemTable() {
        ObservableList<ProgramTm> obList = FXCollections.observableArrayList();
        List<ProgramDTO> programs = programBO.getAll();
        for (ProgramDTO programDto : programs) {
            ProgramTm programTm = new ProgramTm(
                    programDto.getProgramCode(),
                    programDto.getName(),
                    programDto.getPrice(),
                    programDto.getDuration()
            );
            obList.add(programTm);
        }
        tblItem.setItems(obList);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = programBO.delete(new ProgramDTO(txtId.getText(), txtItem.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isDeleted) {
            clearFields();
            setItemTable();
            setCellValueFactory();
            tblItem.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Program deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Program deleted unsuccessfully");
        }
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isSaved = programBO.save(new ProgramDTO(txtId.getText(), txtItem.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isSaved) {
            clearFields();
            txtId.setText(generateItemId());
            setItemTable();
            setCellValueFactory();
            tblItem.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Program saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Program saved unsuccessfully");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isUpdated = programBO.update(new ProgramDTO(txtId.getText(), txtItem.getText(), Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtDuration.getText())));
        if (isUpdated) {
            clearFields();
            setItemTable();
            setCellValueFactory();
            tblItem.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Program updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Program updated unsuccessfully");
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
