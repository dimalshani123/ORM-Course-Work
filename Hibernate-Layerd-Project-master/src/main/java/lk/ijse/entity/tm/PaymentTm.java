package lk.ijse.entity.tm;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentTm {
    private String id;
    private String studentID;
    private String programID;
    private Double fullFee;
    private Double registerPayment;
    private Double totalFee;
}