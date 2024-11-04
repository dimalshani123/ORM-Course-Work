package lk.ijse.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ProgramDTO {
    @Id
    private String itemCode;
    private String name;
    private double price;
    private int duration;
}
