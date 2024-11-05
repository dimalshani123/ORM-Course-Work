package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    List<String> getIds();
    String getCurrentId();
    User getObject(String value);
}
