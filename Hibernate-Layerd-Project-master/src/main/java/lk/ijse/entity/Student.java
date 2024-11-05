package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String address;
    private int tel;
    private String email;
    private Double payed;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_program",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "program_code")
    )
    private Set<Program> programs = new HashSet<>();

    public Student(String id, String name, String address, int tel, String email,Double payed) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.payed=payed;
    }
}
