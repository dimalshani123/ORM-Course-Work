package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.ProgramDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public enum DAOType{
        CUSTOMER,PROGRAM,USER
    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public SuperDAO getDAOType(DAOType boType){
        switch (boType){
            case CUSTOMER :
                return new CustomerDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
