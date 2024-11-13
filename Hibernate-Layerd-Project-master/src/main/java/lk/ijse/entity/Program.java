package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Program {
    @Id
    private String programCode;
    private String name;
    private double price;
    private String duration;

    @OneToMany(mappedBy = "program")
    private Set<Payment> payments = new HashSet<>();

    @ManyToMany(mappedBy = "programs")
    private Set<Student> students = new HashSet<>();

    public Program(String programCode, String name, double price, String duration) {
        this.programCode = programCode;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }
}
