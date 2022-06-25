/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import java.util.ArrayList;
import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.ItemCategoryDTO;
import lk.ijse.ardms.entity.ItemCategory;

/**
 *
 * @author Thushara Supun
 */
public interface ItemCategoryBO extends SuperBO{
    
    public String generateItemCategoryId() throws Exception;
    
    public ArrayList<ItemCategoryDTO> getAll() throws Exception;
    public boolean saveCategory(ItemCategoryDTO itemCategoryDTO) throws Exception;
    public boolean updateCategory(ItemCategoryDTO itemCategoryDTO) throws Exception;
    public boolean deleteCategory(String catid) throws Exception;
    
    public ArrayList<ItemCategoryDTO> search(String searchText) throws Exception;
}
