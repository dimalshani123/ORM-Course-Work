package lk.ijse.bo;

import lk.ijse.bo.custom.impl.PaymentBOImpl;
import lk.ijse.bo.custom.impl.StudentBOImpl;
import lk.ijse.bo.custom.impl.ProgramBOImpl;
import lk.ijse.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){}

    public enum BOType{
        STUDENT,PROGRAM,USER,PAYMENT
    }

    public static BOFactory getBOFactory(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }
    public SuperBO getBOType(BOType boType){
        switch (boType){
            case STUDENT :
                return new StudentBOImpl();
            case PROGRAM :
                return new ProgramBOImpl();
            case USER:
                return new UserBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            default:
                return null;
        }
    }
}
