/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import java.util.ArrayList;
import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.ItemBrandDTO;
import lk.ijse.ardms.dto.ItemCategoryDTO;
import lk.ijse.ardms.dto.ItemManagementDTO;
import lk.ijse.ardms.dto.SupplierManagementDTO;

/**
 *
 * @author Thushara Supun
 */
public interface ItemManagementBO extends SuperBO{
    
    public ArrayList<ItemManagementDTO> getAll() throws Exception;
    
    public String generateItemCode() throws Exception;
    
    public boolean saveItem(ItemManagementDTO itemManagementDTO) throws Exception;
    public boolean updateItem(ItemManagementDTO itemManagementDTO) throws Exception;
    public boolean deleteItem(String itemCode) throws  Exception;
    
    public ItemManagementDTO getOthers(String itemCode) throws Exception;
    public ArrayList<ItemManagementDTO> searchItems(String searchText) throws Exception;
    
    public ArrayList<ItemCategoryDTO> loadCategoryComboBox() throws Exception;
    public ArrayList<ItemBrandDTO> loadBrandComboBox() throws Exception;
    public ArrayList<SupplierManagementDTO> loadSupplierComboBox() throws Exception;
    
}
