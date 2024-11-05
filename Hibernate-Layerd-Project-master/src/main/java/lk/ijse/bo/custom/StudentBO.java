package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean save(StudentDTO customerDto);
    public boolean update(StudentDTO customerDto);
    public boolean delete(StudentDTO object);
    public StudentDTO get(String value);

    List<StudentDTO> getAll();

    List<String> getIds();

    String getCurrentId();
}
