/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import java.util.ArrayList;
import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.CustomerManagementDTO;
import lk.ijse.ardms.entity.CustomerManagement;

/**
 *
 * @author Thushara Supun
 */
public interface CustomerManagementBO extends SuperBO{
    
    public String generateCustomerId() throws Exception;
    
    public ArrayList<CustomerManagementDTO> getAll()throws Exception;
    public boolean saveCustomer(CustomerManagementDTO customerManagement) throws Exception;
    public boolean updateCustomer(CustomerManagementDTO customerManagement) throws Exception;
    public boolean deleteCustomer(String cusid) throws Exception;
    
    public CustomerManagementDTO getOthers(String cusid)throws Exception; 
    
    public ArrayList<CustomerManagementDTO> search(String searchText) throws Exception;
}
