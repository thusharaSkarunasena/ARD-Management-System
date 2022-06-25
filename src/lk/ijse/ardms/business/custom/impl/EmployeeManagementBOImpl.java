/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.ardms.business.custom.EmployeeManagementBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.EmployeeManagementDAO;
import lk.ijse.ardms.dto.EmployeeManagementDTO;
import lk.ijse.ardms.entity.EmployeeManagement;

/**
 *
 * @author Thushara Supun
 */
public class EmployeeManagementBOImpl implements EmployeeManagementBO{
    
    EmployeeManagementDAO employeeManagementDAO=(EmployeeManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.EMPLOYEEMANAGEMENT);

    @Override
    public ArrayList<EmployeeManagementDTO> getAll() throws Exception {
        ArrayList<EmployeeManagement> employeeManagements = employeeManagementDAO.getAll();
        ArrayList<EmployeeManagementDTO> employeeManagementDTOs = new ArrayList<>();
        
        for(EmployeeManagement employee : employeeManagements){
            employeeManagementDTOs.add(new EmployeeManagementDTO(
                    employee.getEmpid(),
                    employee.getName(),
                    employee.getEmployment(),
                    employee.getAddress_no(),
                    employee.getAddress_street(),
                    employee.getAddress_village(),
                    employee.getAddress_city(),
                    employee.getNic(),
                    employee.getTel_home(),
                    employee.getTel_mobile(),
                    employee.getOtherDetails()
            ));
        }
        return employeeManagementDTOs;
    }

    @Override
    public EmployeeManagementDTO getOthers(String empid) throws Exception {
        EmployeeManagement employeeManagement=employeeManagementDAO.getOthers(empid);
        EmployeeManagementDTO employeeManagementDTO = new EmployeeManagementDTO(
                employeeManagement.getEmpid(),
                employeeManagement.getName(),
                employeeManagement.getEmployment(),
                employeeManagement.getAddress_no(),
                employeeManagement.getAddress_street(),
                employeeManagement.getAddress_village(),
                employeeManagement.getAddress_city(),
                employeeManagement.getNic(),
                employeeManagement.getTel_home(),
                employeeManagement.getTel_mobile(),
                employeeManagement.getOtherDetails()
        );
        return employeeManagementDTO;
    }
    
    @Override
    public boolean saveEmployee(EmployeeManagementDTO employeeManagementDTO) throws Exception {
        return employeeManagementDAO.save(new EmployeeManagement(
                employeeManagementDTO.getEmpid(),
                employeeManagementDTO.getName(),
                employeeManagementDTO.getEmployment(),
                employeeManagementDTO.getAddress_no(),
                employeeManagementDTO.getAddress_street(),
                employeeManagementDTO.getAddress_village(),
                employeeManagementDTO.getAddress_city(),
                employeeManagementDTO.getNic(),
                employeeManagementDTO.getTel_home(),
                employeeManagementDTO.getTel_mobile(),
                employeeManagementDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeManagementDTO employeeManagementDTO) throws Exception {
        return employeeManagementDAO.update(new EmployeeManagement(
                employeeManagementDTO.getEmpid(),
                employeeManagementDTO.getName(),
                employeeManagementDTO.getEmployment(),
                employeeManagementDTO.getAddress_no(),
                employeeManagementDTO.getAddress_street(),
                employeeManagementDTO.getAddress_village(),
                employeeManagementDTO.getAddress_city(),
                employeeManagementDTO.getNic(),
                employeeManagementDTO.getTel_home(),
                employeeManagementDTO.getTel_mobile(),
                employeeManagementDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean deleteEmployee(String empid) throws Exception {
        return employeeManagementDAO.delete(empid);
    }

    @Override
    public ArrayList<EmployeeManagementDTO> searchEmployee(String searchText) throws Exception {
        ArrayList<EmployeeManagement> employeeManagements=employeeManagementDAO.searchEmployee(searchText);
        ArrayList<EmployeeManagementDTO> searchEmployees = new ArrayList<>();
        
        for(EmployeeManagement employeeManagement : employeeManagements ){
            
            EmployeeManagementDTO emdto = new EmployeeManagementDTO(
                    employeeManagement.getEmpid(),
                    employeeManagement.getName(),
                    employeeManagement.getEmployment(),
                    employeeManagement.getAddress_no(),
                    employeeManagement.getAddress_street(),
                    employeeManagement.getAddress_village(),
                    employeeManagement.getAddress_city(),
                    employeeManagement.getNic(),
                    employeeManagement.getTel_home(),
                    employeeManagement.getTel_mobile(),
                    employeeManagement.getOtherDetails()
            );
            searchEmployees.add(emdto);
        }
        return searchEmployees;
    }

    @Override
    public String generateEmployeeId() throws Exception {
        return employeeManagementDAO.generateEmployeeId();
    }

    
    
}
