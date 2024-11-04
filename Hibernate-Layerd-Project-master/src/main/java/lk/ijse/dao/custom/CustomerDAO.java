package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Customer;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer> {
    List<String> getIds();
    String getCurrentId();
    Customer getObject(String value);
}
