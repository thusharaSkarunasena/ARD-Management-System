/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import lk.ijse.ardms.dao.SuperDAO;

/**
 *
 * @author Thushara Supun
 */
public interface BillingManagementDAO extends SuperDAO{

    public String getCashierId() throws Exception;
    
    public String generateRefAndBillNo() throws Exception;
}
