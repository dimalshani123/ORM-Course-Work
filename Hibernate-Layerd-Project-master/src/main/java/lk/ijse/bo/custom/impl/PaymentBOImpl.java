package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Payment;


import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
     PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.PAYMENT);


    @Override
    public boolean save(PaymentDTO paymentDTO) {
        return paymentDAO.save(new Payment(paymentDTO.getId(), paymentDTO.getStudentID(), paymentDTO.getProgramID(), paymentDTO.getFullFee(),paymentDTO.getRegisterPayment(),paymentDTO.getTotalFee()));
    }

    @Override
    public boolean update(PaymentDTO paymentDTO) {
        return paymentDAO.update(new Payment(paymentDTO.getId(), paymentDTO.getStudentID(), paymentDTO.getProgramID(), paymentDTO.getFullFee(),paymentDTO.getRegisterPayment(),paymentDTO.getTotalFee()));
    }

    @Override
    public boolean delete(PaymentDTO paymentDTO) {
        return paymentDAO.delete(new Payment(paymentDTO.getId(), paymentDTO.getStudentID(), paymentDTO.getProgramID(), paymentDTO.getFullFee(),paymentDTO.getRegisterPayment(),paymentDTO.getTotalFee()));
    }

    @Override
    public PaymentDTO get(String value) {
        Payment payment = paymentDAO.getObject(value);
        return new PaymentDTO(payment.getId(), payment.getStudentID(), payment.getProgramID(), payment.getFullFee(),payment.getRegisterPayment(),payment.getTotalFee());
    }

    @Override
    public List<String> getIds() {
        return paymentDAO.getIds();
    }

    @Override
    public List<PaymentDTO> getAll() {
        List<Payment> all = paymentDAO.getAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : all){
            paymentDTOS.add(new PaymentDTO(payment.getId(), payment.getStudentID(), payment.getProgramID(), payment.getFullFee(),payment.getRegisterPayment(),payment.getTotalFee()));
        }
        return paymentDTOS;
    }

    @Override
    public String getCurrentId() {
        return paymentDAO.getCurrentId();
    }
}
