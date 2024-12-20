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
    private  String userId;

    // Many-to-One relationship with User (Many students can belong to one user)
    @ManyToOne
    @JoinColumn(name = "user_id") // The foreign key in the Student table
    private User user;

    @ManyToMany
    @JoinTable(
            name = "Student_Program",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "program_code")
    )
    private Set<Program> programs = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Payment> payments = new HashSet<>(); // One-to-many relationship with Payment

    public Student(String id, String name, String address, int tel, String email, Double payed, String userId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
        this.payed = payed;
        this.userId = userId;
    }
}
