package lk.ijse.dao;

//import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.dao.custom.impl.StudentDAOImpl;
import lk.ijse.dao.custom.impl.ProgramDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){}

    public enum DAOType{
        STUDENT,PROGRAM,USER,PAYMENT
    }
    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public SuperDAO getDAOType(DAOType boType){
        switch (boType){
            case STUDENT :
                return new StudentDAOImpl();
            case PROGRAM:
                return new ProgramDAOImpl();
            case USER:
                return new UserDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            default:
                return null;
        }
    }
}
