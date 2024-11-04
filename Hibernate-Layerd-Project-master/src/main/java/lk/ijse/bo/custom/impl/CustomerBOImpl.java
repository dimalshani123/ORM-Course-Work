package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.CUSTOMER);
    @Override
    public boolean save(CustomerDto customerDto) {
       return customerDAO.save(new Customer(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail()));
    }

    @Override
    public boolean update(CustomerDto customerDto) {
        return customerDAO.update( new Customer(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail()));
    }

    @Override
    public boolean delete(CustomerDto customerDto) {
        return customerDAO.delete(new Customer(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail()));
    }

    @Override
    public CustomerDto get(String value) {
        Customer object = customerDAO.getObject(value);
        return new CustomerDto(object.getId(),object.getName(),object.getAddress(),object.getTel(),object.getEmail());
    }

    @Override
    public List<CustomerDto> getAll() {
        List<CustomerDto> customerDtos = new ArrayList<>();
        List<Customer> all = customerDAO.getAll();
        for (Customer customer : all){
            customerDtos.add(new CustomerDto(customer.getId(),customer.getName(),customer.getAddress(),customer.getTel(),customer.getEmail()));
        }
        return customerDtos;
    }
    @Override
    public List<String> getIds() {
        return customerDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return customerDAO.getCurrentId();
    }
}
