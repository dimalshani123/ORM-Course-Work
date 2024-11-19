package lk.ijse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String position;
    private int tel;
    private String email;
    private String password;

    // One-to-Many relationship with Student (One user can have many students)
    @OneToMany(mappedBy = "user") // The "user" field in the Student class is the owning side
    private List<Student> students;

    public User(String id, String name, String position, int tel, String email, String password) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }
}
