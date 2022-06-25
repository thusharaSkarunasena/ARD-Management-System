/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.PlaceCustomerBillDAO;
import lk.ijse.ardms.db.DBConnection;
import lk.ijse.ardms.entity.PlaceCustomerBill;
import lk.ijse.ardms.view.util.tblmodel.BillingManagementTM;

/**
 *
 * @author Thushara Supun
 */
public class PlaceCustomerBillDAOImpl implements PlaceCustomerBillDAO{
    
    @Override
    public boolean placeCustomerBill(PlaceCustomerBill pcb) throws Exception {
        
        Connection connection = DBConnection.getInstance().getConnection();
        
        try{
            connection.setAutoCommit(false);
            
            boolean result1=CrudUtill.executeUpdate("INSERT INTO bill VALUES(?,?,?,?)", pcb.getRefno(), pcb.getBillno(), pcb.getCusid(), pcb.getDate());
            
            if(result1){
               
                boolean result2=false;
                for(BillingManagementTM bmtm : pcb.getBillingManagementTMs()){
                    boolean b1=CrudUtill.executeUpdate("INSERT INTO bill_detail VALUES(?,?,?,?,?)", pcb.getRefno(), bmtm.getItemcode(), bmtm.getUnitPrice(), bmtm.getQty(), bmtm.getTotal());
                    
                        String catid=null;
                        String braid=null;
                        String supid=null;
                        String name=null;
                        String description=null;
                        String size=null;
                        Double price=null;
                        Integer qty=null;
                        String other=null;
                        Integer newQty=null;

                        ResultSet rst=CrudUtill.executeQuery("SELECT * from item where itemcode=?", bmtm.getItemcode());
                        while(rst.next()){
                            catid=rst.getString(2);
                            braid=rst.getString(3);
                            supid=rst.getString(4);
                            name=rst.getString(5);
                            description=rst.getString(6);
                            size=rst.getString(7);
                            price=rst.getDouble(8);
                            qty=rst.getInt(9);
                            other=rst.getString(10);
                        }
                        newQty=qty-bmtm.getQty();
                        
                        boolean b2=CrudUtill.executeUpdate("UPDATE item set catid=?, braid=?, supid=?, name=?, description=?, size=?, price=?, qty=?, other_details=? where itemcode=?", catid, braid, supid, name, description, size, price, newQty, other, bmtm.getItemcode());
                        if(b1 & b2){
                            result2=true;
                        }else{
                            result2=false;
                            break;
                        }
                }
                if(result2){
                    boolean result3=CrudUtill.executeUpdate("INSERT INTO customer_payment VALUES(?,?,?,?,?,?,?,?)", pcb.getBillPaymentNo(), pcb.getRefno(), pcb.getEmpid(), pcb.getPaymentType(), pcb.getTot_Amount(), pcb.getDiscount(), pcb.getNet_Amount(), pcb.getPayble_total());
                    System.out.println(result3);
                    if(result3){
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        }finally{
            connection.setAutoCommit(true);
        }
        
    }
    
}