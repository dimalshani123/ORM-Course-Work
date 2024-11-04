package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ProgramBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAO programDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.PROGRAM);
    @Override
    public boolean save(ProgramDTO programDTO) {
        return programDAO.save(new Program(programDTO.getProgramCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public boolean update(ProgramDTO programDTO) {
        return programDAO.update( new Program(programDTO.getProgramCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public boolean delete(ProgramDTO programDTO) {
        return programDAO.delete(new Program(programDTO.getProgramCode(), programDTO.getName(), programDTO.getPrice(), programDTO.getDuration()));
    }

    @Override
    public ProgramDTO get(String value) {
        Program program = programDAO.getObject(value);
        return new ProgramDTO(program.getProgramCode(), program.getName(), program.getPrice(), program.getDuration());
    }

    @Override
    public List<String> getIds() {
        return programDAO.getIds();
    }

    @Override
    public List<ProgramDTO> getAll() {
        List<Program> all = programDAO.getAll();
        List<ProgramDTO> programDtos = new ArrayList<>();
        for (Program item : all){
            programDtos.add(new ProgramDTO(item.getProgramCode(), item.getName(), item.getPrice(), item.getDuration()));
        }
        return programDtos;
    }

    @Override
    public String getCurrentId() {
        return programDAO.getCurrentId();
    }
}
