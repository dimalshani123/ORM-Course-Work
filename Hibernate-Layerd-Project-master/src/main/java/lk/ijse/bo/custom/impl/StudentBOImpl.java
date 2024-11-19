package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.StudentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.StudentDAO;
import lk.ijse.dto.StudentDTO;
import lk.ijse.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.STUDENT);
    @Override
    public boolean save(StudentDTO customerDto) {
       return studentDAO.save(new Student(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail(),customerDto.getPayed(),customerDto.getUserId()));
    }

    @Override
    public boolean update(StudentDTO customerDto) {
        return studentDAO.update( new Student(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail(),customerDto.getPayed(),customerDto.getUserId()));
    }

    @Override
    public boolean delete(StudentDTO customerDto) {
        return studentDAO.delete(new Student(customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getTel(),customerDto.getEmail(),customerDto.getPayed(),customerDto.getUserId()));
    }

    @Override
    public StudentDTO get(String value) {
        Student object = studentDAO.getObject(value);
        return new StudentDTO(object.getId(),object.getName(),object.getAddress(),object.getTel(),object.getEmail(),object.getPayed(),object.getUserId());
    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> customerDtos = new ArrayList<>();
        List<Student> all = studentDAO.getAll();
        for (Student customer : all){
            customerDtos.add(new StudentDTO(customer.getId(),customer.getName(),customer.getAddress(),customer.getTel(),customer.getEmail(),customer.getPayed(),customer.getUserId()));
        }
        return customerDtos;
    }
    @Override
    public List<String> getIds() {
        return studentDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return studentDAO.getCurrentId();
    }
}
