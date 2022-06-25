/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.ardms.business.custom.CustomerManagementBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.CustomerManagementDAO;
import lk.ijse.ardms.dto.CustomerManagementDTO;
import lk.ijse.ardms.entity.CustomerManagement;

/**
 *
 * @author Thushara Supun
 */
public class CustomerManagementBOImpl implements CustomerManagementBO{

   CustomerManagementDAO customerManagementDAO=(CustomerManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMERMANAGEMENT);
    
   @Override
    public String generateCustomerId() throws Exception {
        return customerManagementDAO.generateCustomerId();
    }
    
   @Override
    public boolean saveCustomer(CustomerManagementDTO customerManagementDTO) throws Exception {
        CustomerManagement customerManagement1 = new CustomerManagement(customerManagementDTO.getCusid(),customerManagementDTO.getName(),customerManagementDTO.getAddress_no(),customerManagementDTO.getAddress_street(),customerManagementDTO.getAddress_village(),customerManagementDTO.getAddress_city(),customerManagementDTO.getNic(),customerManagementDTO.getTel_home(),customerManagementDTO.getTel_mobile(),customerManagementDTO.getEmail());
        return customerManagementDAO.save(customerManagement1);
    }
 
    
    @Override
    public boolean updateCustomer(CustomerManagementDTO customerManagement) throws Exception {
        CustomerManagement customerManagement2 = new CustomerManagement(customerManagement.getCusid(),customerManagement.getName(),customerManagement.getAddress_no(),customerManagement.getAddress_street(),customerManagement.getAddress_village(),customerManagement.getAddress_city(),customerManagement.getNic(),customerManagement.getTel_home(),customerManagement.getTel_mobile(),customerManagement.getEmail());
        return customerManagementDAO.update(customerManagement2);
    }
    
    
    @Override
    public boolean deleteCustomer(String cusid) throws Exception {
        return customerManagementDAO.delete(cusid);
    }

    
    @Override
    public ArrayList<CustomerManagementDTO> getAll() throws Exception {
        ArrayList<CustomerManagement> customerManagements=customerManagementDAO.getAll();
        ArrayList<CustomerManagementDTO> customerManagementDTOs=new ArrayList<>();
        
        for (CustomerManagement customerManagement : customerManagements) {
            
            customerManagementDTOs.add(new CustomerManagementDTO(customerManagement.getCusid(), customerManagement.getName(),customerManagement.getAddress_no(),customerManagement.getAddress_street(),customerManagement.getAddress_village(),customerManagement.getAddress_city(),customerManagement.getNic(),customerManagement.getTel_home(),customerManagement.getTel_mobile(),customerManagement.getEmail()));
        }
        return customerManagementDTOs;
    }

    @Override
    public CustomerManagementDTO getOthers(String cusid) throws Exception {
        CustomerManagement customerManagement=customerManagementDAO.getOthers(cusid);
        return new CustomerManagementDTO(customerManagement.getCusid(),customerManagement.getName(),customerManagement.getAddress_no(),customerManagement.getAddress_street(),customerManagement.getAddress_village(),customerManagement.getAddress_city(),customerManagement.getNic(),customerManagement.getTel_home(),customerManagement.getTel_mobile(),customerManagement.getEmail());
    }

    @Override
    public ArrayList<CustomerManagementDTO> search(String searchText) throws Exception {
       ArrayList<CustomerManagement> customerManagements=customerManagementDAO.search(searchText);
        ArrayList<CustomerManagementDTO> searchCustomers = new ArrayList<>();
        
        for(CustomerManagement customerManagement : customerManagements ){
            
            CustomerManagementDTO cmdto = new CustomerManagementDTO(
                    customerManagement.getCusid(),
                    customerManagement.getName(),
                    customerManagement.getAddress_no(),
                    customerManagement.getAddress_street(),
                    customerManagement.getAddress_village(),
                    customerManagement.getAddress_city(),
                    customerManagement.getNic(),
                    customerManagement.getTel_home(),
                    customerManagement.getTel_mobile(),
                    customerManagement.getEmail()
            );
            searchCustomers.add(cmdto);
        }
        return searchCustomers;
    }
    
}
