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
    private String itemCode;
    private String name;
    private double price;
    private int duration;


    public Program(String itemCode, String name, double price, int duration) {
        this.itemCode = itemCode;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }
}
