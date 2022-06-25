/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import java.util.ArrayList;
import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.EmployeeManagementDTO;

/**
 *
 * @author Thushara Supun
 */
public interface EmployeeManagementBO extends SuperBO{
    
    public String generateEmployeeId() throws Exception;
    
    public ArrayList<EmployeeManagementDTO> getAll() throws Exception;
    public boolean saveEmployee(EmployeeManagementDTO employeeManagementDTO) throws Exception;
    public boolean updateEmployee(EmployeeManagementDTO employeeManagementDTO) throws Exception;
    public boolean deleteEmployee(String empid) throws Exception;
    
    public EmployeeManagementDTO getOthers(String empid) throws Exception;
    public ArrayList<EmployeeManagementDTO> searchEmployee(String searchText) throws Exception;
}
