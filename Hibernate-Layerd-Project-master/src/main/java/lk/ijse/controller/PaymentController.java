package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.custom.impl.ProgramDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import lk.ijse.entity.tm.PaymentTm;

import java.util.List;

public class PaymentController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PaymentTm, String> clmFinal;

    @FXML
    private TableColumn<PaymentTm, Double> clmFullFee;

    @FXML
    private TableColumn<PaymentTm, String> clmId;

    @FXML
    private TableColumn<PaymentTm, Double> clmLastPay;

    @FXML
    private TableColumn<PaymentTm, String> clmPId;

    @FXML
    private TableColumn<PaymentTm, String> clmSID;

    @FXML
    private ComboBox<String> cmbProgramId;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtProgramFee;

    @FXML
    private TextField txtSLastPayment;

    private PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.PAYMENT);
    private ProgramDAOImpl programDAOImpl = new ProgramDAOImpl();
    private StudentDAOImpl studentDAOImpl = new StudentDAOImpl();

    private List<Program> programsList;
    private List<Student> studentsList;

    public void initialize() throws Exception {
        setPaymentTable();
        setCellValueFactory();
        tableSelection();
        generatePaymentId();

        programsList = programDAOImpl.getAll();
        for (Program program : programsList) {
            cmbProgramId.getItems().add(program.getProgramCode());
        }

        studentsList = studentDAOImpl.getAll();
        for (Student student : studentsList) {
            cmbStudentId.getItems().add(student.getId());
        }

        cmbStudentId.setOnAction(this::loadStudent);
        cmbProgramId.setOnAction(this::loadProgram);
    }

    private void setPaymentTable() {
        ObservableList<PaymentTm> paymentList = FXCollections.observableArrayList();
        List<PaymentDTO> paymentDTOs = paymentBO.getAll();
        for (PaymentDTO dto : paymentDTOs) {
            paymentList.add(new PaymentTm(
                    dto.getId(),
                    dto.getStudentID(),
                    dto.getProgramID(),
                    dto.getFullFee(),
                    dto.getRegisterPayment(),
                    dto.getTotalFee()
            ));
        }
        tblPayment.setItems(paymentList);
    }

    private void setCellValueFactory() {
        clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clmSID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        clmPId.setCellValueFactory(new PropertyValueFactory<>("programID"));
        clmFullFee.setCellValueFactory(new PropertyValueFactory<>("fullFee"));
        clmLastPay.setCellValueFactory(new PropertyValueFactory<>("registerPayment"));
        clmFinal.setCellValueFactory(new PropertyValueFactory<>("totalFee"));
    }

    private void tableSelection() {
        tblPayment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtId.setText(newValue.getId());
                cmbStudentId.getSelectionModel().select(newValue.getStudentID());
                cmbProgramId.getSelectionModel().select(newValue.getProgramID());
                txtProgramFee.setText(String.valueOf(newValue.getFullFee()));
                txtSLastPayment.setText(String.valueOf(newValue.getRegisterPayment()));
                txtAmount.setText(String.valueOf(newValue.getTotalFee()));
            }
        });
    }

    private String generatePaymentId() {
        try {
            String currentId = paymentBO.getCurrentId();
            if (currentId != null && currentId.matches("PP\\d{3}")) {
                int idNum = Integer.parseInt(currentId.substring(2)) + 1;
                String newId = String.format("PP%03d", idNum);
                txtId.setText(newId);
                return newId;
            } else {
                txtId.setText("PP001");
                return "PP001";
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error generating ID").show();
        }
        return null;
    }

    void clearFields() {
        txtId.clear();
        cmbStudentId.getSelectionModel().clearSelection();
        cmbProgramId.getSelectionModel().clearSelection();
        txtProgramFee.clear();
        txtSLastPayment.clear();
        txtAmount.clear();
    }

    public void setAmount() {
        try {
            double programFee = Double.parseDouble(txtProgramFee.getText());
            double studentRegisterFee = Double.parseDouble(txtSLastPayment.getText());
            double amount = programFee - studentRegisterFee;
            txtAmount.setText(String.valueOf(amount));
        } catch (NumberFormatException e) {
            txtAmount.clear();
        }
    }

    public void loadStudent(ActionEvent actionEvent) {
        String selectedStudentId = cmbStudentId.getSelectionModel().getSelectedItem();
        if (selectedStudentId != null) {
            Student selectedStudent = studentsList.stream()
                    .filter(student -> student.getId().equals(selectedStudentId))
                    .findFirst()
                    .orElse(null);

            if (selectedStudent != null) {
                txtSLastPayment.setText(String.valueOf(selectedStudent.getPayed()));
            } else {
                txtSLastPayment.clear();
            }
        }
        setAmount();
    }

    public void loadProgram(ActionEvent actionEvent) {
        String selectedProgramId = cmbProgramId.getSelectionModel().getSelectedItem();
        if (selectedProgramId != null) {
            Program selectedProgram = programsList.stream()
                    .filter(program -> program.getProgramCode().equals(selectedProgramId))
                    .findFirst()
                    .orElse(null);

            if (selectedProgram != null) {
                txtProgramFee.setText(String.valueOf(selectedProgram.getPrice()));
            } else {
                txtProgramFee.clear();
            }
        }
        setAmount();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        boolean isDeleted = paymentBO.delete(new PaymentDTO(txtId.getText(), cmbStudentId.getValue(),cmbProgramId.getValue(), Double.parseDouble(txtProgramFee.getText()), Double.parseDouble(txtSLastPayment.getText()),Double.parseDouble(txtAmount.getText())));
        if (isDeleted) {
            clearFields();
            setPaymentTable();
            setCellValueFactory();
            tblPayment.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Payment deleted successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment deleted unsuccessfully");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isSaved = paymentBO.save(new PaymentDTO(txtId.getText(), cmbStudentId.getValue(),cmbProgramId.getValue(), Double.parseDouble(txtProgramFee.getText()), Double.parseDouble(txtSLastPayment.getText()),Double.parseDouble(txtAmount.getText())));
        if (isSaved) {
            clearFields();
            txtId.setText(generatePaymentId());
            setPaymentTable();
            setCellValueFactory();
            tblPayment.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Payment saved successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment saved unsuccessfully");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isUpdated = paymentBO.update(new PaymentDTO(txtId.getText(), cmbStudentId.getValue(),cmbProgramId.getValue(), Double.parseDouble(txtProgramFee.getText()), Double.parseDouble(txtSLastPayment.getText()),Double.parseDouble(txtAmount.getText())));
        if (isUpdated) {
            clearFields();
            setPaymentTable();
            setCellValueFactory();
            tblPayment.refresh();
            new Alert(Alert.AlertType.CONFIRMATION, "Payment updated successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Payment updated unsuccessfully");
        }
    }

    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtProgramFeeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSLastPaymentOnAction(ActionEvent event) {
    }

    @FXML
    void cmbProgramOnAction(ActionEvent event) {
    }

    @FXML
    void cmbStudentOnAction(ActionEvent event) {
    }
}
