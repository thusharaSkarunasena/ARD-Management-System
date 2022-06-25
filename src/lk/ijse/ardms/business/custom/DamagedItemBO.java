/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import java.util.ArrayList;
import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.DamagedItemDTO;

/**
 *
 * @author Thushara Supun
 */
public interface DamagedItemBO extends SuperBO{
    
    public ArrayList<DamagedItemDTO> getAllDamagedItem() throws Exception;
    
    public boolean add(DamagedItemDTO damagedItemDTO) throws Exception;
    
    public boolean remove(String itemcode) throws Exception;
    
    public ArrayList<DamagedItemDTO> searchDamagedItems(String searchText) throws Exception;
    
    public boolean update(DamagedItemDTO damagedItemDTO) throws Exception;
    
    public boolean restore(String itemcode) throws Exception;
    
}
