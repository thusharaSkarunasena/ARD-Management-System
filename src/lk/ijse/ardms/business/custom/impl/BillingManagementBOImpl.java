/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import lk.ijse.ardms.business.custom.BillingManagementBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.BillingManagementDAO;

/**
 *
 * @author Thushara Supun
 */
public class BillingManagementBOImpl implements BillingManagementBO{

    BillingManagementDAO billingManagementDAO=(BillingManagementDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BILLINGMANAGEMENT);
    
    @Override
    public String getCashierId() throws Exception {
        return billingManagementDAO.getCashierId();
    }

    @Override
    public String generateRefAndBillNo() throws Exception {
        return billingManagementDAO.generateRefAndBillNo();
    }
    
}
