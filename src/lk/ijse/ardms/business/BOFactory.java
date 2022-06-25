/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business;

import lk.ijse.ardms.business.custom.impl.BillingManagementBOImpl;
import lk.ijse.ardms.business.custom.impl.CustomerManagementBOImpl;
import lk.ijse.ardms.business.custom.impl.CustomerReturnsBOImpl;
import lk.ijse.ardms.business.custom.impl.DamagedItemBOImpl;
import lk.ijse.ardms.business.custom.impl.EmployeeManagementBOImpl;
import lk.ijse.ardms.business.custom.impl.ItemBrandBOImpl;
import lk.ijse.ardms.business.custom.impl.ItemCategoryBOImpl;
import lk.ijse.ardms.business.custom.impl.ItemManagementBOImpl;
import lk.ijse.ardms.business.custom.impl.LogInBOImpl;
import lk.ijse.ardms.business.custom.impl.PlaceCustomerBillBOImpl;
import lk.ijse.ardms.business.custom.impl.SupplierManagementBOImpl;

/**
 *
 * @author Thushara Supun
 */
public class BOFactory {
    
    public enum BOTypes{
        CUSTOMERMANAGEMENT, ITEMMANAGEMENT, ITEMCATEGORY, EMPLOYEEMANAGEMENT, SUPPLIERMANAGEMENT, ITEMBRAND, DAMAGEDITEM, LOGIN, BILLINGMANAGEMENT, CUSTOMERRETURNS, PLACECUSTOMERBILL;
    }
    
    private static BOFactory bOFactory;

    public BOFactory() {
    }
    
    public static BOFactory getInstance(){
        if(bOFactory==null){
            bOFactory = new BOFactory();
        }
        return bOFactory;
    }
    
    public SuperBO getBO(BOTypes boTypes){
        switch(boTypes){
            case CUSTOMERMANAGEMENT:
                return new CustomerManagementBOImpl();
            case ITEMMANAGEMENT:
                return new ItemManagementBOImpl();
            case ITEMCATEGORY:
                return new ItemCategoryBOImpl();
            case EMPLOYEEMANAGEMENT:
                return new EmployeeManagementBOImpl();
            case SUPPLIERMANAGEMENT:
                return new SupplierManagementBOImpl();
            case ITEMBRAND:
                return new ItemBrandBOImpl();
            case DAMAGEDITEM:
                return new DamagedItemBOImpl();
            case LOGIN:
                return new LogInBOImpl();
            case BILLINGMANAGEMENT:
                return new BillingManagementBOImpl();
            case CUSTOMERRETURNS:
                return new CustomerReturnsBOImpl();
            case PLACECUSTOMERBILL:
                return new PlaceCustomerBillBOImpl();
            default:
                return  null;
        }
    }
}
