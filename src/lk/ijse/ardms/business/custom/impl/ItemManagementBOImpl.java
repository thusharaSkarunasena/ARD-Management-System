/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import java.util.ArrayList;
import lk.ijse.ardms.business.custom.ItemManagementBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.ItemManagementDAO;
import lk.ijse.ardms.dto.ItemBrandDTO;
import lk.ijse.ardms.dto.ItemCategoryDTO;
import lk.ijse.ardms.dto.ItemManagementDTO;
import lk.ijse.ardms.dto.SupplierManagementDTO;
import lk.ijse.ardms.entity.ItemBrand;
import lk.ijse.ardms.entity.ItemCategory;
import lk.ijse.ardms.entity.ItemManagement;
import lk.ijse.ardms.entity.SupplierManagement;

/**
 *
 * @author Thushara Supun
 */
public class ItemManagementBOImpl implements ItemManagementBO{

    ItemManagementDAO itemManagementDAO = (ItemManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEMMANAGEMENT);
    
    @Override
    public ArrayList<ItemManagementDTO> getAll() throws Exception {
        ArrayList<ItemManagement> itemManagements = itemManagementDAO.getAll();
        ArrayList<ItemManagementDTO> itemManagementDTOs = new ArrayList<>();
        
        for(ItemManagement item : itemManagements){
            itemManagementDTOs.add(new ItemManagementDTO(
                    item.getItemcode(),
                    item.getCatName(),
                    item.getBraName(),
                    item.getSupName(),
                    item.getName(),
                    item.getDescription(),
                    item.getSize(),
                    item.getPrice(),
                    item.getQty(),
                    item.getOther_details()
            ));
        }
        return itemManagementDTOs;
    }

    
    @Override
    public ItemManagementDTO getOthers(String itemCode) throws Exception {
        ItemManagement itemManagement=itemManagementDAO.getOthers(itemCode);
        return new ItemManagementDTO(
                itemManagement.getItemcode(),
                itemManagement.getCatName(),
                itemManagement.getBraName(),
                itemManagement.getSupName(),
                itemManagement.getName(),
                itemManagement.getDescription(),
                itemManagement.getSize(),
                itemManagement.getPrice(),
                itemManagement.getQty(),
                itemManagement.getOther_details()
        );
    }
    
    
    @Override
    public boolean saveItem(ItemManagementDTO itemManagementDTO) throws Exception {
        return itemManagementDAO.save(new ItemManagement(
                itemManagementDTO.getItemcode(),
                itemManagementDTO.getCatName(),
                itemManagementDTO.getBraName(),
                itemManagementDTO.getSupName(),
                itemManagementDTO.getName(),
                itemManagementDTO.getDescription(),
                itemManagementDTO.getSize(),
                itemManagementDTO.getPrice(),
                itemManagementDTO.getQty(),
                itemManagementDTO.getOther_details()
        ));
    }

    
    @Override
    public boolean updateItem(ItemManagementDTO itemManagementDTO) throws Exception {
        return itemManagementDAO.update(new ItemManagement(
                itemManagementDTO.getItemcode(),
                itemManagementDTO.getCatName(),
                itemManagementDTO.getBraName(),
                itemManagementDTO.getSupName(),
                itemManagementDTO.getName(),
                itemManagementDTO.getDescription(),
                itemManagementDTO.getSize(),
                itemManagementDTO.getPrice(),
                itemManagementDTO.getQty(),
                itemManagementDTO.getOther_details()
        ));
    }

    
    @Override
    public boolean deleteItem(String itemCode) throws Exception {
        return itemManagementDAO.delete(itemCode);
    }

    
    @Override
    public ArrayList<ItemManagementDTO> searchItems(String searchText) throws Exception {
        ArrayList<ItemManagement> itemManagements=itemManagementDAO.searchItems(searchText);
        ArrayList<ItemManagementDTO> searchItems = new ArrayList<>();
        
        for(ItemManagement itemManagement : itemManagements ){
            ItemManagementDTO imdto = new ItemManagementDTO(
                    itemManagement.getItemcode(),
                    itemManagement.getCatName(),
                    itemManagement.getBraName(),
                    itemManagement.getSupName(),
                    itemManagement.getName(),
                    itemManagement.getDescription(),
                    itemManagement.getSize(),
                    itemManagement.getPrice(),
                    itemManagement.getQty(),
                    itemManagement.getOther_details()
            );
            searchItems.add(imdto);
        }
        return searchItems;
    }

    @Override
    public ArrayList<ItemCategoryDTO> loadCategoryComboBox() throws Exception {
        ArrayList<ItemCategory> itemCategorys=itemManagementDAO.loadCategoryComboBox();
        
        ArrayList<ItemCategoryDTO> itemCategoryDTOs=new ArrayList<>();
        
        for (ItemCategory category : itemCategorys) {
            itemCategoryDTOs.add(new ItemCategoryDTO(category.getCatid(), category.getName(), category.getDescription(), category.getOther_details()));
        }
        
        return itemCategoryDTOs;
    }

    @Override
    public ArrayList<ItemBrandDTO> loadBrandComboBox() throws Exception {
        ArrayList<ItemBrand> itemBrands=itemManagementDAO.loadBrandComboBox();
        
        ArrayList<ItemBrandDTO> itemBrandDTOs=new ArrayList<>();
        
        for(ItemBrand brand : itemBrands){
            itemBrandDTOs.add(new ItemBrandDTO(
                    brand.getBraId(),
                    brand.getName(),
                    brand.getDescription(),
                    brand.getOtherDetails()
            ));
        }
        return itemBrandDTOs;
    }

    @Override
    public ArrayList<SupplierManagementDTO> loadSupplierComboBox() throws Exception {
        ArrayList<SupplierManagement> supplierManagements=itemManagementDAO.loadSupplierComboBox();
        
        ArrayList<SupplierManagementDTO> supplierManagementDTOs=new ArrayList<>();
        
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
    public String generateItemCode() throws Exception {
        return itemManagementDAO.generateItemCode();
    }
    
}
