package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ProgramDTO;

import java.util.List;

public interface ProgramBO extends SuperBO {
    public boolean save(ProgramDTO itemDto);
    public boolean update(ProgramDTO itemDto);
    public boolean delete(ProgramDTO itemDto);
    public ProgramDTO get(String value);

    List<String> getIds();

    List<ProgramDTO> getAll();

    String getCurrentId();
}
