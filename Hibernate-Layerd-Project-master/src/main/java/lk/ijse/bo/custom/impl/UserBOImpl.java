package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.USER);
    @Override
    public boolean save(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getId(),userDTO.getName(),userDTO.getPossition(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return userDAO.update( new User(userDTO.getId(),userDTO.getName(),userDTO.getPossition(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }

    @Override
    public boolean delete(UserDTO userDTO) {
        return userDAO.delete(new User(userDTO.getId(),userDTO.getName(),userDTO.getPossition(),userDTO.getTel(),userDTO.getEmail(),userDTO.getPassword()));
    }

    @Override
    public UserDTO get(String value) {
        User object = userDAO.getObject(value);
        return new UserDTO(object.getId(),object.getName(),object.getPosition(),object.getTel(),object.getEmail(),object.getPassword());
    }

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> all = userDAO.getAll();
        for (User user : all){
            userDTOS.add(new UserDTO(user.getId(),user.getName(),user.getPosition(),user.getTel(),user.getEmail(),user.getPassword()));
        }
        return userDTOS;
    }
    @Override
    public List<String> getIds() {
        return userDAO.getIds();
    }

    @Override
    public String getCurrentId() {
        return userDAO.getCurrentId();
    }
}
