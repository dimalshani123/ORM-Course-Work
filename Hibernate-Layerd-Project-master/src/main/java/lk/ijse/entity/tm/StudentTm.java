package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentTm {
    private String id;
    private String name;
    private String address;
    private int tel;
    private String email;
    private Double payed;
}
