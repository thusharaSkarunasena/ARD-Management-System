/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.CustomerManagementDAO;
import lk.ijse.ardms.entity.CustomerManagement;

/**
 *
 * @author Thushara Supun
 */
public class CustomerManagementDAOImpl implements CustomerManagementDAO{

    @Override
    public String generateCustomerId() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("select cusid from customer order by 1 desc limit 1;");
        String lastCusId=null;
        Integer NpartDCount=0;
        
        if(rst.next()){
            lastCusId=rst.getString(1);
            
            String[] output = lastCusId.split("-");
        
            Integer Npart=Integer.parseInt(output[1]);
            Npart=Npart+1;
            Integer testNpart=Npart;
        
        
            while(testNpart!=0){
                testNpart=testNpart/10;
                NpartDCount++;
            }
        
            String nextCustId="CUS-";
        
            Integer rounds=5-NpartDCount;
        
            while(rounds!=0){
                nextCustId=nextCustId+"0";
                rounds--;
            }
        
            nextCustId=nextCustId+""+Npart;
        
            return nextCustId;
        }else{
            return "CUS-00001";
        }
    }
    
    @Override
    public ArrayList<CustomerManagement> getAll() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from customer");
        ArrayList<CustomerManagement> customerManagements=new ArrayList<>();
        while(rst.next()){
            customerManagements.add(new CustomerManagement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)
            ));
            
        }
        return customerManagements;
    }

    @Override
    public CustomerManagement get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(CustomerManagement entity) throws Exception {
        return CrudUtill.executeUpdate("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?,?)", entity.getCusid(),entity.getName(),entity.getAddress_no(),entity.getAddress_street(),entity.getAddress_village(),entity.getAddress_city(),entity.getNic(),entity.getTel_home(),entity.getTel_mobile(),entity.getEmail());
    }

    @Override
    public boolean update(CustomerManagement entity) throws Exception {
        return CrudUtill.executeUpdate("UPDATE customer set name=?, address_no=?, address_street=?, address_village=?, address_city=?, nic=?, tel_home=?, tel_mobile=?, email=? where cusid=?", entity.getName(),entity.getAddress_no(),entity.getAddress_street(),entity.getAddress_village(),entity.getAddress_city(),entity.getNic(),entity.getTel_home(),entity.getTel_mobile(),entity.getEmail(),entity.getCusid());
    }

    @Override
    public boolean delete(String cusid) throws Exception {
        return CrudUtill.executeUpdate("DELETE from customer where cusid=?", cusid);
    }

    @Override
    public CustomerManagement getOthers(String cusid) throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from customer where cusid=?",cusid);
        
        while(rst.next()){
            return new CustomerManagement(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10));
            
        }
        return null;
    }

    @Override
    public ArrayList<CustomerManagement> search(String searchText) throws Exception {
        ArrayList<CustomerManagement> searchCustomers = new ArrayList<>();
        ResultSet rst = CrudUtill.executeQuery("SELECT * from customer where cusid like '%"+searchText+"%' or name like '%"+searchText+"%' or nic like '%"+searchText+"%' ");
        
        while (rst.next()) {
            CustomerManagement customerManagement = new CustomerManagement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10));
            searchCustomers.add(customerManagement);
        }
        return searchCustomers;
    }
    
}
