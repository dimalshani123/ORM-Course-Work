package lk.ijse.entity.tm;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class PaymentTm {
    @Id
    private String id;
    private String studentID;
    private String programID;
    private Double fullFee;
    private Double registerPayment;
    private Double totalFee;
}