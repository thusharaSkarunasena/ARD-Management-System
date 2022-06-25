/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudDAO;
import lk.ijse.ardms.entity.SupplierManagement;

/**
 *
 * @author Thushara Supun
 */
public interface SupplierManagementDAO extends CrudDAO<SupplierManagement, String>{
    
    public String generateSupplierId() throws Exception;
    
    public SupplierManagement getOthers(String supId) throws Exception;
    
    public ArrayList<SupplierManagement> searchSupplier(String searchText) throws Exception;
    
}
