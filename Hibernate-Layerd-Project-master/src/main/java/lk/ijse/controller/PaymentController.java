package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
//import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.custom.impl.ProgramDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;
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
    private TableColumn<?, ?> clmFinal;

    @FXML
    private TableColumn<?, ?> clmFullFee;

    @FXML
    private TableColumn<?, ?> clmId;

    @FXML
    private TableColumn<?, ?> clmLastPay;

    @FXML
    private TableColumn<?, ?> clmPId;

    @FXML
    private TableColumn<?, ?> clmSID;

    @FXML
    private ComboBox<String> cmbProgramId;  // Specify the type here

    @FXML
    private ComboBox<String> cmbStudentId;  // Specify the type here

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

//    PaymentBO paymentBO = (PaymentBO) BOFactory.getBOFactory().getBOType(BOFactory.BOType.PAYMENT);

    private ProgramDAOImpl programDAOImpl = new ProgramDAOImpl();
    private StudentDAOImpl studentDAOImpl = new StudentDAOImpl();

    private List<Program> programsList;
    private List<Student> studentsList;

    public void initialize() throws Exception {
//        setPaymentTable();
//        setCellValueFactory();
//        tableSelection();
//        generatePaymentId();
        // Load programs into the combo box
        programsList = programDAOImpl.getAll();
        for (Program program : programsList) {
            cmbProgramId.getItems().add(program.getProgramCode());
            // Use ProgramCode as ID

        }

        // Load students into the combo box
        studentsList = studentDAOImpl.getAll();
        for (Student student : studentsList) {
            cmbStudentId.getItems().add(student.getId()); // Use Student ID
        }

        // Set up combo box event handlers
        cmbStudentId.setOnAction(this::loadStudent);
        cmbProgramId.setOnAction(this::loadProgram);
    }

    void clearFields() {
        txtId.clear();
        cmbStudentId.getItems().clear();
        cmbProgramId.getItems().clear();
        txtProgramFee.clear();
        txtSLastPayment.clear();
        txtAmount.clear();
    }

//    private String generatePaymentId() {
//        try {
//            String currentId = paymentBO.getCurrentId();
//            if (currentId != null && currentId.matches("P\\d{3}")) {
//                // Extract the numeric part and increment it
//                int idNum = Integer.parseInt(currentId.substring(1)); // Removes the 'P' prefix and parses the number
//                idNum++; // Increment the number
//                String newId = String.format("P%03d", idNum); // Format the number as Pxxx (e.g., P001, P002)
//                txtId.setText(newId);
//                return newId;
//            } else {
//                // If currentId is null or format is not recognized, start from P001
//                txtId.setText("P1");
//                return "P1";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Error generating ID").show();
//        }
//
//        return null;
//    }
//
//    private void tableSelection() {
//        tblPayment.setOnMouseClicked(event -> {
//            int index = tblPayment.getSelectionModel().getSelectedIndex();
//            txtId.setText(String.valueOf(clmId.getCellData(index)));
//            cmbStudentId.setValue(String.valueOf(clmSID.getCellData(index))); // Use setValue for ComboBox
//            cmbProgramId.setValue(String.valueOf(clmPId.getCellData(index))); // Use setValue for ComboBox
//            txtProgramFee.setText(String.valueOf(clmFullFee.getCellData(index)));
//            txtSLastPayment.setText(String.valueOf(clmLastPay.getCellData(index)));
//            txtAmount.setText(String.valueOf(clmFinal.getCellData(index)));
//        });
//    }
//
//    private void setCellValueFactory() {
//        clmId.setCellValueFactory(new PropertyValueFactory<>("programCode"));
//        clmSID.setCellValueFactory(new PropertyValueFactory<>("student Id"));
//        clmPId.setCellValueFactory(new PropertyValueFactory<>("program Id"));
//        clmFullFee.setCellValueFactory(new PropertyValueFactory<>("full pay"));
//        clmLastPay.setCellValueFactory(new PropertyValueFactory<>("last pay"));
//        clmFinal.setCellValueFactory(new PropertyValueFactory<>("final"));
//    }
//
//    private void setPaymentTable() {
//        tblPayment.setOnMouseClicked(event -> {
//            // Get the selected index from the table view
//            int index = tblPayment.getSelectionModel().getSelectedIndex();
//            if (index != -1) { // Ensure a row is selected
//                // Get the selected PaymentTm from the table
//                PaymentTm selectedPayment = tblPayment.getItems().get(index);
//
//                // Update the TextField and ComboBox values with the data from the selected row
//                txtId.setText(selectedPayment.getId());  // Set the Payment ID
//
//                cmbStudentId.setValue(selectedPayment.getStudentID());  // Set the Student ID in ComboBox
//
//                cmbProgramId.setValue(selectedPayment.getProgramID());  // Set the Program ID in ComboBox
//
//                txtProgramFee.setText(String.valueOf(selectedPayment.getFullFee()));  // Set Program Fee
//
//                txtSLastPayment.setText(String.valueOf(selectedPayment.getRegisterPayment()));  // Set Last Payment
//
//                txtAmount.setText(String.valueOf(selectedPayment.getTotalFee()));  // Set the Total Amount
//            }
//        });
//    }
//


    // Method to load student details when a student is selected
    public void loadStudent(ActionEvent actionEvent) {
        String selectedStudentId = cmbStudentId.getSelectionModel().getSelectedItem();
        if (selectedStudentId != null) {
            // Find the student with the selected ID from the studentsList
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
    }

    // Method to load program details when a program is selected
    public void loadProgram(ActionEvent actionEvent) {
        String selectedProgramId = cmbProgramId.getSelectionModel().getSelectedItem();
        if (selectedProgramId != null) {
            // Find the program with the selected ProgramCode from the programsList
            Program selectedProgram = programsList.stream()
                    .filter(program -> program.getProgramCode().equals(selectedProgramId))
                    .findFirst()
                    .orElse(null);

            if (selectedProgram != null) {
                txtProgramFee.setText(String.valueOf(selectedProgram.getPrice())); // Program fee
            } else {
                txtProgramFee.clear();
            }
        }
    }

    // Handler for Delete button
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        // Handle delete logic here
    }

    // Handler for Save button
    @FXML
    void btnSaveOnAction(ActionEvent event) {
        // Handle save logic here
    }

    // Handler for Update button
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        // Handle update logic here
    }

    // Handler for Amount TextField
    @FXML
    void txtAmountOnAction(ActionEvent event) {
        // Handle amount input if necessary
    }

    // Handler for Id TextField
    @FXML
    void txtIdOnAction(ActionEvent event) {
        // Handle ID input if necessary
    }

    // Handler for Program Fee TextField
    @FXML
    void txtProgramFeeOnAction(ActionEvent event) {
        // Handle program fee input if necessary
    }

    // Handler for Last Payment TextField
    @FXML
    void txtSLastPaymentOnAction(ActionEvent event) {
        // Handle last payment input if necessary
    }

    @FXML
    void cmbProgramOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStudentOnAction(ActionEvent event) {

    }
}
