package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Program;
import lk.ijse.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    List<String> getIds();

    String getCurrentId();

    Student getObject(String value);
    boolean update(Session session, Student object);
}
