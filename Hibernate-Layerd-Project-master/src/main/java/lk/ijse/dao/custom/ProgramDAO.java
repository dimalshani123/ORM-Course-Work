package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Program;
import org.hibernate.Session;

import java.util.List;

public interface ProgramDAO extends CrudDAO<Program> {
    List<String> getIds();

    String getCurrentId();

    Program getObject(String value);
    boolean update(Session session, Program object);
}
