/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudDAO;
import lk.ijse.ardms.entity.EmployeeManagement;

/**
 *
 * @author Thushara Supun
 */
public interface EmployeeManagementDAO extends CrudDAO<EmployeeManagement, String>{
    
    public String generateEmployeeId() throws Exception;
    
    public EmployeeManagement getOthers(String empid) throws Exception;
    
    public ArrayList<EmployeeManagement> searchEmployee(String searchText) throws Exception;
}
