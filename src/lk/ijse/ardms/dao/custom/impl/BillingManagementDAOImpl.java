/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.time.LocalDate;
import lk.ijse.ardms.controller.LogInController;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.BillingManagementDAO;

/**
 *
 * @author Thushara Supun
 */
public class BillingManagementDAOImpl implements BillingManagementDAO{

    @Override
    public String getCashierId() throws Exception {
        String username=LogInController.username;
        String cashierId=null;
        
        ResultSet rst=CrudUtill.executeQuery("SELECT empid from userpass where username=?", username);
        
        while(rst.next()){
            cashierId=rst.getString(1);
        }
        return cashierId;
    }
    
    
    public static String getUserName() throws Exception{
        
        String username=LogInController.username;
        String userId=null;
        String userName=null;
        
        ResultSet rst1=CrudUtill.executeQuery("SELECT empid from userpass where username=?", username);
        
        while(rst1.next()){
            userId=rst1.getString(1);
        }
        
        ResultSet rst2=CrudUtill.executeQuery("SELECT name from employee where empid=?", userId);
        
        while(rst2.next()){
            userName=rst2.getString(1);
        }
        
        return userName;
    }

    @Override
    public String generateRefAndBillNo() throws Exception {
        
        String nextBillNo=null;
        Integer nextbillNo=null;
        String nextRefNo=null;
        String nextBillAndRefNo=null;
        
        ResultSet rst1=CrudUtill.executeQuery("select billno from bill order by 1 desc limit 1;");
        ResultSet rst2=CrudUtill.executeQuery("select date from bill order by 1 desc limit 1;");
        String lastBillNo=null;
        String lastBillDate=null;
        String nowDate=LocalDate.now().toString();
        Integer BNpartDCount=0;
        
        while(rst2.next()){
            lastBillDate=rst2.getString(1);
        }
        
        if(rst1.next()){
          if(lastBillDate.equals(nowDate)){
              lastBillNo=rst1.getString(1);
              nextbillNo=Integer.parseInt(lastBillNo)+1;
              nextBillNo=Integer.toString(nextbillNo);
          }else{
              nextBillNo="1";
          }
        }else{
           nextBillNo="1";
        }
        
        
        ResultSet rst3=CrudUtill.executeQuery("select refno from bill order by 1 desc limit 1;");
        String lastRefNo=null;
        Integer NpartDCount=0;
        
        if(rst3.next()){
            lastRefNo=rst3.getString(1);
            
            String[] output = lastRefNo.split("-");
        
            Integer Npart=Integer.parseInt(output[1]);
            Npart=Npart+1;
            Integer testNpart=Npart;
        
        
            while(testNpart!=0){
                testNpart=testNpart/10;
                NpartDCount++;
            }
        
            nextRefNo="Ref-";
        
            Integer rounds=8-NpartDCount;
        
            while(rounds!=0){
                nextRefNo=nextRefNo+"0";
                rounds--;
            }
        
            nextRefNo=nextRefNo+""+Npart;
        }else{
            nextRefNo="Ref-00000001";
        }
        
        nextBillAndRefNo=nextBillNo+","+nextRefNo;
        
        return nextBillAndRefNo;
    }
    
}
