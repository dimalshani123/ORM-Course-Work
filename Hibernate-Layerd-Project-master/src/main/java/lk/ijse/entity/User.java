package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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



    public User(String id, String name, String position, int tel, String email,String password) {
        this.id=id;
        this.name=name;
        this.position=position;
        this.tel=tel;
        this.email=email;
        this.password=password;
    }
}
