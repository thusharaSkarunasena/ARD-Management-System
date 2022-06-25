/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import lk.ijse.ardms.business.custom.PlaceCustomerBillBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.PlaceCustomerBillDAO;
import lk.ijse.ardms.dto.BillPaymentsDTO;
import lk.ijse.ardms.dto.BillingManagementDTO;
import lk.ijse.ardms.entity.PlaceCustomerBill;

/**
 *
 * @author Thushara Supun
 */
public class PlaceCustomerBillBOImpl implements PlaceCustomerBillBO{

    
    @Override
    public boolean placeCustomerBill(BillingManagementDTO bmdto, BillPaymentsDTO bpdto) throws Exception {
        PlaceCustomerBillDAO pcbdao=(PlaceCustomerBillDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PLACECUSTOMERBILL);
        
        return pcbdao.placeCustomerBill(new PlaceCustomerBill(
                bmdto.getRefno(),
                bmdto.getBillno(),
                bmdto.getCusid(),
                bmdto.getDate(),
                bmdto.getEmpid(),
                
                bmdto.getBillingManagementTMs(),
                
                bmdto.getTot_Amount(),
                bmdto.getDiscount(),
                bmdto.getNet_Amount(),
                
                bpdto.getBillPaymentNo(),
                bpdto.getPaymentType(),
                bpdto.getPayble_total()
        ));
    }
    
}
