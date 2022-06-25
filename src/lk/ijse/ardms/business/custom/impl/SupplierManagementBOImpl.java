/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.ardms.business.custom.SupplierManagementBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.SupplierManagementDAO;
import lk.ijse.ardms.dto.SupplierManagementDTO;
import lk.ijse.ardms.entity.SupplierManagement;

/**
 *
 * @author Thushara Supun
 */
public class SupplierManagementBOImpl implements SupplierManagementBO{

    SupplierManagementDAO supplierManagementDAO=(SupplierManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.SUPPLIERMANAGEMENT);
    
    @Override
    public ArrayList<SupplierManagementDTO> getAll() throws Exception {
        ArrayList<SupplierManagement> supplierManagements = supplierManagementDAO.getAll();
        ArrayList<SupplierManagementDTO> supplierManagementDTOs = new ArrayList<>();
        
        for(SupplierManagement supplier : supplierManagements){
            supplierManagementDTOs.add(new SupplierManagementDTO(
                    supplier.getSupid(),
                    supplier.getName(),
                    supplier.getCompany(),
                    supplier.getAddress_no(),
                    supplier.getAddress_street(),
                    supplier.getAddress_village(),
                    supplier.getAddress_city(),
                    supplier.getNic(),
                    supplier.getTel_office(),
                    supplier.getTel_mobile(),
                    supplier.getEmail(),
                    supplier.getOtherDetails()
            ));
        }
        return supplierManagementDTOs;
    }
    
    @Override
    public SupplierManagementDTO getOthers(String supId) throws Exception {
        SupplierManagement supplierManagement = supplierManagementDAO.getOthers(supId);
        
        return new SupplierManagementDTO(
                supplierManagement.getSupid(),
                supplierManagement.getName(),
                supplierManagement.getCompany(),
                supplierManagement.getAddress_no(),
                supplierManagement.getAddress_street(),
                supplierManagement.getAddress_village(),
                supplierManagement.getAddress_city(),
                supplierManagement.getNic(),
                supplierManagement.getTel_office(),
                supplierManagement.getTel_mobile(),
                supplierManagement.getEmail(),
                supplierManagement.getOtherDetails()
        );
    }

    @Override
    public boolean saveSupplier(SupplierManagementDTO supplierManagementDTO) throws Exception {
        return supplierManagementDAO.save(new SupplierManagement(
                supplierManagementDTO.getSupid(),
                supplierManagementDTO.getName(),
                supplierManagementDTO.getCompany(),
                supplierManagementDTO.getAddress_no(),
                supplierManagementDTO.getAddress_street(),
                supplierManagementDTO.getAddress_village(),
                supplierManagementDTO.getAddress_city(),
                supplierManagementDTO.getNic(),
                supplierManagementDTO.getTel_office(),
                supplierManagementDTO.getTel_mobile(),
                supplierManagementDTO.getEmail(),
                supplierManagementDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean updateSupplier(SupplierManagementDTO supplierManagementDTO) throws Exception {
        return supplierManagementDAO.update(new SupplierManagement(
                supplierManagementDTO.getSupid(),
                supplierManagementDTO.getName(),
                supplierManagementDTO.getCompany(),
                supplierManagementDTO.getAddress_no(),
                supplierManagementDTO.getAddress_street(),
                supplierManagementDTO.getAddress_village(),
                supplierManagementDTO.getAddress_city(),
                supplierManagementDTO.getNic(),
                supplierManagementDTO.getTel_office(),
                supplierManagementDTO.getTel_mobile(),
                supplierManagementDTO.getEmail(),
                supplierManagementDTO.getOtherDetails()
        ));
    }

    @Override
    public boolean deleteSuppier(String supId) throws Exception {
        return supplierManagementDAO.delete(supId);
    }

    @Override
    public ArrayList<SupplierManagementDTO> searchSupplier(String searchText) throws Exception {
        ArrayList<SupplierManagement> supplierManagements=supplierManagementDAO.searchSupplier(searchText);
        ArrayList<SupplierManagementDTO> searchSuppliers = new ArrayList<>();
        
        for(SupplierManagement supplierManagement : supplierManagements ){
            SupplierManagementDTO smdto = new SupplierManagementDTO(
                    supplierManagement.getSupid(),
                    supplierManagement.getName(),
                    supplierManagement.getCompany(),
                    supplierManagement.getAddress_no(),
                    supplierManagement.getAddress_street(),
                    supplierManagement.getAddress_village(),
                    supplierManagement.getAddress_city(),
                    supplierManagement.getNic(),
                    supplierManagement.getTel_office(),
                    supplierManagement.getTel_mobile(),
                    supplierManagement.getEmail(),
                    supplierManagement.getOtherDetails()
            );
            searchSuppliers.add(smdto);
        }
        return searchSuppliers;
    }

    @Override
    public String generateSupplierId() throws Exception {
       return supplierManagementDAO.generateSupplierId();
    }
    
}
