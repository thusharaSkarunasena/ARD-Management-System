/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao;

import lk.ijse.ardms.dao.custom.impl.BillingManagementDAOImpl;
import lk.ijse.ardms.dao.custom.impl.CustomerManagementDAOImpl;
import lk.ijse.ardms.dao.custom.impl.CustomerReturnsDAOImpl;
import lk.ijse.ardms.dao.custom.impl.DamagedItemDAOImpl;
import lk.ijse.ardms.dao.custom.impl.EmployeeManagementDAOImpl;
import lk.ijse.ardms.dao.custom.impl.ItemBrandDAOImpl;
import lk.ijse.ardms.dao.custom.impl.ItemCategoryDAOImpl;
import lk.ijse.ardms.dao.custom.impl.ItemManagementDAOImpl;
import lk.ijse.ardms.dao.custom.impl.LogInDAOImpl;
import lk.ijse.ardms.dao.custom.impl.PlaceCustomerBillDAOImpl;
import lk.ijse.ardms.dao.custom.impl.SupplierManagementDAOImpl;

/**
 *
 * @author Thushara Supun
 */
public class DAOFactory {
    
    public enum DAOTypes{
        
        CUSTOMERMANAGEMENT, ITEMMANAGEMENT, ITEMCATEGORY, EMPLOYEEMANAGEMENT, SUPPLIERMANAGEMENT, ITEMBRAND, DAMAGEDITEM, LOGIN, BILLINGMANAGEMENT, CUSTOMERRETURNS, PLACECUSTOMERBILL;
    
    }
    
    private static DAOFactory dAOFactory;

    public DAOFactory() {
    }
    
    public static DAOFactory getInstance(){
        if(dAOFactory == null){
            dAOFactory = new DAOFactory();
        }
        return dAOFactory;
    }
    
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch(daoTypes){
            case CUSTOMERMANAGEMENT:
                return new CustomerManagementDAOImpl();
            case ITEMMANAGEMENT:
                return new ItemManagementDAOImpl();
            case ITEMCATEGORY:
                return new ItemCategoryDAOImpl();
            case EMPLOYEEMANAGEMENT:
                return new EmployeeManagementDAOImpl();
            case SUPPLIERMANAGEMENT:
                return new SupplierManagementDAOImpl();
            case ITEMBRAND:
                return new ItemBrandDAOImpl();
            case DAMAGEDITEM:
                return new DamagedItemDAOImpl();
            case LOGIN:
                return new LogInDAOImpl();
            case BILLINGMANAGEMENT:
                return new BillingManagementDAOImpl();
            case CUSTOMERRETURNS:
                return new CustomerReturnsDAOImpl();
            case PLACECUSTOMERBILL:
                return new PlaceCustomerBillDAOImpl();
            default:
                return null;
        }
    }
}
