/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import lk.ijse.ardms.dao.SuperDAO;
import lk.ijse.ardms.entity.PlaceCustomerBill;

/**
 *
 * @author Thushara Supun
 */
public interface PlaceCustomerBillDAO extends SuperDAO{
    
    public boolean placeCustomerBill(PlaceCustomerBill pcb) throws Exception;
    
}
