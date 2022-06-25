/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.ardms.business.custom.DamagedItemBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.DamagedItemDAO;
import lk.ijse.ardms.dto.DamagedItemDTO;
import lk.ijse.ardms.entity.DamagedItem;

/**
 *
 * @author Thushara Supun
 */
public class DamagedItemBOImpl implements DamagedItemBO{

    DamagedItemDAO damagedItemDAO = (DamagedItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DAMAGEDITEM);
    
    @Override
    public ArrayList<DamagedItemDTO> getAllDamagedItem() throws Exception {
        ArrayList<DamagedItem> damagedItems = damagedItemDAO.getAllDamagedItems();
        
        ArrayList<DamagedItemDTO> damagedItemDTOs = new ArrayList<>();
        
        for(DamagedItem damagedItem : damagedItems){
            damagedItemDTOs.add(new DamagedItemDTO(
                    damagedItem.getItemcode(),
                    damagedItem.getName(),
                    damagedItem.getSize(),
                    damagedItem.getCategory(),
                    damagedItem.getQty(),
                    damagedItem.getDetails()
            ));
        }
        return damagedItemDTOs;
    }

    @Override
    public boolean add(DamagedItemDTO damagedItemDTO) throws Exception {
        DamagedItem damagedItem=new DamagedItem();
        damagedItem.setItemcode(damagedItemDTO.getItemcode());
        damagedItem.setQty(damagedItemDTO.getQty());
        damagedItem.setDetails(damagedItemDTO.getDetails());
    
        return damagedItemDAO.add(damagedItem);
    }

    @Override
    public boolean remove(String itemcode) throws Exception {
        return damagedItemDAO.remove(itemcode);
    }

    @Override
    public ArrayList<DamagedItemDTO> searchDamagedItems(String searchText) throws Exception {
        ArrayList<DamagedItem> damagedItems = damagedItemDAO.searchDamagedItems(searchText);
        ArrayList<DamagedItemDTO> damagedItemDTOs = new ArrayList<>();
        
        for(DamagedItem damagedItem : damagedItems){
            damagedItemDTOs.add(new DamagedItemDTO(
                    damagedItem.getItemcode(),
                    damagedItem.getName(),
                    damagedItem.getSize(),
                    damagedItem.getCategory(),
                    damagedItem.getQty(),
                    damagedItem.getDetails()
            ));
        }
        return damagedItemDTOs;
    }

    @Override
    public boolean update(DamagedItemDTO damagedItemDTO) throws Exception {
        DamagedItem damagedItem=new DamagedItem();
        damagedItem.setItemcode(damagedItemDTO.getItemcode());
        damagedItem.setName(damagedItemDTO.getName());
        damagedItem.setQty(damagedItemDTO.getQty());
        damagedItem.setDetails(damagedItemDTO.getDetails());
        
        return damagedItemDAO.update(damagedItem);
    }

    @Override
    public boolean restore(String itemcode) throws Exception {
        return damagedItemDAO.restore(itemcode);
    }

    
}
