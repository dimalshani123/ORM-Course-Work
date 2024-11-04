package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ProgramDAO;
import lk.ijse.dto.ProgramDTO;
import lk.ijse.entity.Program;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ProgramDAO itemDAO = (ProgramDAO) DAOFactory.getDaoFactory().getDAOType(DAOFactory.DAOType.ITEM);
    @Override
    public boolean save(ProgramDTO itemDto) {
        return itemDAO.save(new Program(itemDto.getItemCode(), itemDto.getName(), itemDto.getPrice(), itemDto.getDuration()));
    }

    @Override
    public boolean update(ProgramDTO itemDto) {
        return itemDAO.update( new Program(itemDto.getItemCode(), itemDto.getName(), itemDto.getPrice(), itemDto.getDuration()));
    }

    @Override
    public boolean delete(ProgramDTO itemDto) {
        return itemDAO.delete(new Program(itemDto.getItemCode(), itemDto.getName(), itemDto.getPrice(), itemDto.getDuration()));
    }

    @Override
    public ProgramDTO get(String value) {
        Program item = itemDAO.getObject(value);
        return new ProgramDTO(item.getItemCode(), item.getName(), item.getPrice(), item.getDuration());
    }

    @Override
    public List<String> getIds() {
        return itemDAO.getIds();
    }

    @Override
    public List<ProgramDTO> getAll() {
        List<Program> all = itemDAO.getAll();
        List<ProgramDTO> itemDtos = new ArrayList<>();
        for (Program item : all){
            itemDtos.add(new ProgramDTO(item.getItemCode(), item.getName(), item.getPrice(), item.getDuration()));
        }
        return itemDtos;
    }

    @Override
    public String getCurrentId() {
        return itemDAO.getCurrentId();
    }
}
