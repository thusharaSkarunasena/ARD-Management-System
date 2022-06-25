/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import java.util.ArrayList;
import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.ItemBrandDTO;

/**
 *
 * @author Thushara Supun
 */
public interface ItemBrandBO extends SuperBO{
    
    public String generateItemBrandId() throws Exception;
    
    public ArrayList<ItemBrandDTO> getAll() throws Exception;
    public boolean saveBrand(ItemBrandDTO itemBrandDTO) throws Exception;
    public boolean updateBrand(ItemBrandDTO itemBrandDTO) throws Exception;
    public boolean deleteBrand(String braId) throws Exception;
    
    public ArrayList<ItemBrandDTO> searchBrand(String searchText) throws Exception;
    
}
