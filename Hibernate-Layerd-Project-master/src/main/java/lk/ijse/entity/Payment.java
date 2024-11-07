package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Payment {
    @Id
    private String id;
    private String studentID;
    private String programID;
    private Double fullFee;
    private Double registerPayment;
    private Double totalFee;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_code", referencedColumnName = "programCode", insertable = false, updatable = false)
    private Program program;

    public Payment(String id, String studentID, String programID, double fullFee, double registerPayment, double totalFee) {
        this.id = id;
        this.studentID = studentID;
        this.programID = programID;
        this.fullFee = fullFee;
        this.registerPayment = registerPayment;
        this.totalFee = totalFee;
    }
}
