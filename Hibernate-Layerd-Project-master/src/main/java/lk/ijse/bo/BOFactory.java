package lk.ijse.bo;

import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.bo.custom.impl.ProgramBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public enum BOType{
        CUSTOMER,PROGRAM,USER
    }

    public static BOFactory getBOFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }
    public SuperBO getBOType(BOType boType){
        switch (boType){
            case CUSTOMER :
                return new CustomerBOImpl();
            case PROGRAM :
                return new ProgramBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
