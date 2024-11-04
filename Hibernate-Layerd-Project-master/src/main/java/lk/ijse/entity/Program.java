package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
public class Program {
    @Id
    private String programCode;
    private String name;
    private double price;
    private int duration;


    public Program(String programCode, String name, double price, int duration) {
        this.programCode = programCode;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }
}
