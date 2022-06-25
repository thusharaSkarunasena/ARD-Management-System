/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import lk.ijse.ardms.business.SuperBO;
import lk.ijse.ardms.dto.BillPaymentsDTO;
import lk.ijse.ardms.dto.BillingManagementDTO;

/**
 *
 * @author Thushara Supun
 */
public interface PlaceCustomerBillBO extends SuperBO{
    
    public boolean placeCustomerBill(BillingManagementDTO bmdto, BillPaymentsDTO bpdto) throws Exception;
    
}
