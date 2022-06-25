/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudDAO;
import lk.ijse.ardms.entity.ItemBrand;

/**
 *
 * @author Thushara Supun
 */
public interface ItemBrandDAO extends CrudDAO<ItemBrand, String>{
    
    public String generateItemBrandId() throws Exception;
    
    public ArrayList<ItemBrand> searchBrand(String searchText) throws Exception;
}
