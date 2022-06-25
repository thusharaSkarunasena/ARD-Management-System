/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudDAO;
import lk.ijse.ardms.entity.ItemBrand;
import lk.ijse.ardms.entity.ItemCategory;
import lk.ijse.ardms.entity.ItemManagement;
import lk.ijse.ardms.entity.SupplierManagement;

/**
 *
 * @author Thushara Supun
 */
public interface ItemManagementDAO extends CrudDAO<ItemManagement, String>{
    
    public String generateItemCode() throws Exception;
    
    public ItemManagement getOthers(String itemCode) throws Exception;
    
    public ArrayList<ItemManagement> searchItems(String searchText) throws Exception;
    
    public ArrayList<ItemCategory> loadCategoryComboBox() throws Exception;
    
    public ArrayList<ItemBrand> loadBrandComboBox() throws Exception;
    
    public ArrayList< SupplierManagement> loadSupplierComboBox() throws Exception;
    
}
