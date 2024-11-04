package lk.ijse.dao;

import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.ProgramDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public enum DAOType{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL
    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public SuperDAO getDAOType(DAOType boType){
        switch (boType){
            case CUSTOMER :
                return new CustomerDAOImpl();
            case ITEM:
                return new ProgramDAOImpl();
            default:
                return null;
        }
    }
}
