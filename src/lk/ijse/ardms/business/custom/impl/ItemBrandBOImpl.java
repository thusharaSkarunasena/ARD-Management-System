/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.ardms.business.custom.ItemBrandBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.ItemBrandDAO;
import lk.ijse.ardms.dto.ItemBrandDTO;
import lk.ijse.ardms.entity.ItemBrand;

/**
 *
 * @author Thushara Supun
 */
public class ItemBrandBOImpl implements ItemBrandBO{
    
    ItemBrandDAO itemBrandDAO = (ItemBrandDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEMBRAND);

    @Override
    public ArrayList<ItemBrandDTO> getAll() throws Exception {
        ArrayList<ItemBrand> itemBrands = itemBrandDAO.getAll();
        ArrayList<ItemBrandDTO> itemBrandDTOs = new ArrayList<>();
        
        for(ItemBrand brand : itemBrands){
            itemBrandDTOs.add(new ItemBrandDTO(
                    brand.getBraId(),
                    brand.getName(),
                    brand.getDescription(),
                    brand.getOtherDetails()
            ));
        }
        return itemBrandDTOs;
    }

    @Override
    public boolean saveBrand(ItemBrandDTO itemBrandDTO) throws Exception {
        return itemBrandDAO.save(new ItemBrand(
                itemBrandDTO.getBraId(),
                itemBrandDTO.getName(),
                itemBrandDTO.getDescription(),
                itemBrandDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean updateBrand(ItemBrandDTO itemBrandDTO) throws Exception {
        return itemBrandDAO.update(new ItemBrand(
                itemBrandDTO.getBraId(),
                itemBrandDTO.getName(),
                itemBrandDTO.getDescription(),
                itemBrandDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean deleteBrand(String braId) throws Exception {
        return itemBrandDAO.delete(braId);
    }

    @Override
    public ArrayList<ItemBrandDTO> searchBrand(String searchText) throws Exception {
        ArrayList<ItemBrand> itemBrands=itemBrandDAO.searchBrand(searchText);
        ArrayList<ItemBrandDTO> itemBrandDTOs = new ArrayList<>();
        
        for(ItemBrand brand : itemBrands ){
            ItemBrandDTO ibdto = new ItemBrandDTO(
                    brand.getBraId(),
                    brand.getName(),
                    brand.getDescription(),
                    brand.getOtherDetails()
            );
            itemBrandDTOs.add(ibdto);
        }
        return itemBrandDTOs;
    }

    @Override
    public String generateItemBrandId() throws Exception {
        return itemBrandDAO.generateItemBrandId();
    }
    
}
