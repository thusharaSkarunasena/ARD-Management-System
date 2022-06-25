/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import java.util.ArrayList;
import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.SupplierManagementDTO;

/**
 *
 * @author Thushara Supun
 */
public interface SupplierManagementBO extends SuperBO{
    
    public String generateSupplierId() throws Exception;
    
    public ArrayList<SupplierManagementDTO> getAll() throws Exception;
    public boolean saveSupplier(SupplierManagementDTO supplierManagementDTO) throws Exception;
    public boolean updateSupplier(SupplierManagementDTO supplierManagementDTO) throws Exception;
    public boolean deleteSuppier(String supId) throws Exception;
    
    public SupplierManagementDTO getOthers(String supId) throws Exception;
    public ArrayList<SupplierManagementDTO> searchSupplier(String searchText) throws Exception;
    
}
