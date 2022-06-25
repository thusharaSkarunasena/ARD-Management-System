/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudDAO;
import lk.ijse.ardms.entity.ItemCategory;

/**
 *
 * @author Thushara Supun
 */
public interface ItemCategoryDAO extends CrudDAO<ItemCategory, String>{
    
    public String generateItemCategoryId() throws Exception;
    
    public ArrayList<ItemCategory> search(String searchText) throws Exception;

}
