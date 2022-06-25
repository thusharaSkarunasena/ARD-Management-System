/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudDAO;
import lk.ijse.ardms.entity.CustomerManagement;

/**
 *
 * @author Thushara Supun
 */
public interface CustomerManagementDAO extends CrudDAO<CustomerManagement, String>{
    
    public String generateCustomerId() throws Exception;
    
    public CustomerManagement getOthers(String cusid) throws Exception;
    
    public ArrayList<CustomerManagement> search(String searchText) throws Exception;
    

    
}
