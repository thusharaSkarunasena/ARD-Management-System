/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.ardms.business.custom.ItemCategoryBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.ItemCategoryDAO;
import lk.ijse.ardms.dto.ItemCategoryDTO;
import lk.ijse.ardms.entity.ItemCategory;

/**
 *
 * @author Thushara Supun
 */
public class ItemCategoryBOImpl implements ItemCategoryBO{
    
    public ItemCategoryDAO itemCategoryDAO=(ItemCategoryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEMCATEGORY);
    
    @Override
    public ArrayList<ItemCategoryDTO> getAll() throws Exception {
        ArrayList<ItemCategory> itemCategorys=itemCategoryDAO.getAll();
        ArrayList<ItemCategoryDTO> itemCategoryDTOs = new ArrayList<>();
        
        for(ItemCategory category : itemCategorys){
            itemCategoryDTOs.add(new ItemCategoryDTO(category.getCatid(),category.getName(),category.getDescription(),category.getOther_details()));
        }
        return itemCategoryDTOs;
    }

    @Override
    public boolean saveCategory(ItemCategoryDTO itemCategoryDTO) throws Exception {
        ItemCategory itemCategory1=new ItemCategory(itemCategoryDTO.getCatid(),itemCategoryDTO.getName(),itemCategoryDTO.getDescription(),itemCategoryDTO.getOther_details());
        return itemCategoryDAO.save(itemCategory1);
    }

    @Override
    public boolean updateCategory(ItemCategoryDTO itemCategoryDTO) throws Exception {
        ItemCategory itemCategory2=new ItemCategory(itemCategoryDTO.getCatid(),itemCategoryDTO.getName(),itemCategoryDTO.getDescription(),itemCategoryDTO.getOther_details());
        return itemCategoryDAO.update(itemCategory2);
    }

    @Override
    public boolean deleteCategory(String catid) throws Exception {
        return itemCategoryDAO.delete(catid);
    }

    @Override
    public ArrayList<ItemCategoryDTO> search(String searchText) throws Exception {
        ArrayList<ItemCategory> itemCategorys=itemCategoryDAO.search(searchText);
        ArrayList<ItemCategoryDTO> itemCategoryDTOs = new ArrayList<>();
        
        for(ItemCategory itemCategory : itemCategorys ){
            
            ItemCategoryDTO icdto = new ItemCategoryDTO(
                    itemCategory.getCatid(),
                    itemCategory.getName(),
                    itemCategory.getDescription(),
                    itemCategory.getOther_details()
                    
            );
            itemCategoryDTOs.add(icdto);
        }
        return itemCategoryDTOs;
    }

    @Override
    public String generateItemCategoryId() throws Exception {
        return itemCategoryDAO.generateItemCategoryId();
    }
    
}
