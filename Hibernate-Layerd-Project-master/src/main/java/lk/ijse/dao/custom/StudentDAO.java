package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Student;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    List<String> getIds();
    String getCurrentId();
    Student getObject(String value);
}
