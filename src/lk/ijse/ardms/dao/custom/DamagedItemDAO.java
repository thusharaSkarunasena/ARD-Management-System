/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudDAO;
import lk.ijse.ardms.entity.DamagedItem;

/**
 *
 * @author Thushara Supun
 */
public interface DamagedItemDAO extends CrudDAO<DamagedItem, String>{
    
    public ArrayList<DamagedItem> getAllDamagedItems() throws Exception;
    
    public boolean add(DamagedItem damagedItem) throws Exception;
    
    public boolean remove(String itemcode) throws Exception;
    
    public ArrayList<DamagedItem> searchDamagedItems(String searchText) throws Exception;
    
    public boolean restore(String itemcode) throws Exception;
    
}
