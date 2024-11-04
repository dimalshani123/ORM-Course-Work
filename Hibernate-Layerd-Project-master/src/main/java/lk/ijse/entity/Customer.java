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
public class Customer {
    @Id
    private String id;
    private String name;
    private String address;
    private int tel;
    private String email;



    public Customer(String id, String name, String address, int tel, String email) {
        this.id=id;
        this.name=name;
        this.address=address;
        this.tel=tel;
        this.email=email;
    }
}
