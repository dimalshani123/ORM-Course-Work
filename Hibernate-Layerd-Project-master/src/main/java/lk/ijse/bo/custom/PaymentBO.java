package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PaymentDTO;


import java.util.List;

public interface PaymentBO extends SuperBO {
    public boolean save(PaymentDTO paymentDTO);
    public boolean update(PaymentDTO paymentDTO);
    public boolean delete(PaymentDTO paymentDTO);
    public PaymentDTO get(String value);

    List<String> getIds();

    List<PaymentDTO> getAll();

    String getCurrentId();
}
