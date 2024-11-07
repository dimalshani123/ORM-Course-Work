package lk.ijse.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PaymentDTO  {
    @Id
    private String id;
    private String studentID;
    private String programID;
    private double fullFee;
    private double registerPayment;
    private double totalFee;

}

