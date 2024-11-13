package lk.ijse.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramTm {
    private String programCode;
    private String name;
    private double price;
    private String duration;
}
