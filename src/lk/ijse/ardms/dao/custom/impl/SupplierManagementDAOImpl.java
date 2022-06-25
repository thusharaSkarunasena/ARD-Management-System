/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.SupplierManagementDAO;
import lk.ijse.ardms.entity.SupplierManagement;

/**
 *
 * @author Thushara Supun
 */
public class SupplierManagementDAOImpl implements SupplierManagementDAO{

    @Override
    public ArrayList<SupplierManagement> getAll() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from supplier");
        
        ArrayList<SupplierManagement> supplierManagements = new ArrayList<>();
        
        while(rst.next()){
            supplierManagements.add(new SupplierManagement(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5),
                rst.getString(6),
                rst.getString(7),
                rst.getString(8),
                rst.getString(9),
                rst.getString(10),
                rst.getString(11),
                rst.getString(12)
            ));
        }
        return supplierManagements;
    }
    
    @Override
    public SupplierManagement getOthers(String supId) throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from supplier where supid=?", supId);
        
        while(rst.next()){
            return new SupplierManagement(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5),
                rst.getString(6),
                rst.getString(7),
                rst.getString(8),
                rst.getString(9),
                rst.getString(10),
                rst.getString(11),
                rst.getString(12)
            );
        }
        return null;
    }
    
    @Override
    public SupplierManagement get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(SupplierManagement entity) throws Exception {
        return CrudUtill.executeUpdate("INSERT INTO supplier values(?,?,?,?,?,?,?,?,?,?,?,?)", entity.getSupid(), entity.getName(), entity.getCompany(), entity.getAddress_no(), entity.getAddress_street(), entity.getAddress_village(), entity.getAddress_city(), entity.getNic(), entity.getTel_office(), entity.getTel_mobile(), entity.getEmail(), entity.getOtherDetails());
    }

    @Override
    public boolean update(SupplierManagement entity) throws Exception {
        return CrudUtill.executeUpdate("UPDATE supplier set name=?, company=?, address_no=?, address_street=?, address_village=?, address_city=?, nic=?, tel_office=?, tel_mobile=?, email=?, other=? where supid=?", entity.getName(), entity.getCompany(), entity.getAddress_no(), entity.getAddress_street(), entity.getAddress_village(), entity.getAddress_city(), entity.getNic(), entity.getTel_office(), entity.getTel_mobile(), entity.getEmail(), entity.getOtherDetails(), entity.getSupid());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtill.executeUpdate("DELETE from supplier where supid=?", id);
    }
    
    @Override
    public ArrayList<SupplierManagement> searchSupplier(String searchText) throws Exception {
        ArrayList<SupplierManagement> searchSuppliers = new ArrayList<>();
        ResultSet rst = CrudUtill.executeQuery("SELECT * from supplier where supid like '%"+searchText+"%' or name like '%"+searchText+"%' or company like '%"+searchText+"%' ");
        
        while (rst.next()) {
            SupplierManagement supplierManagement = new SupplierManagement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getString(12));
            searchSuppliers.add(supplierManagement);
        }
        return searchSuppliers;
    }

    @Override
    public String generateSupplierId() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("select supid from supplier order by 1 desc limit 1;");
        String lastSupId=null;
        Integer NpartDCount=0;
        
        if(rst.next()){
            lastSupId=rst.getString(1);
            
            String[] output = lastSupId.split("-");
        
            Integer Npart=Integer.parseInt(output[1]);
            Npart=Npart+1;
            Integer testNpart=Npart;
        
        
            while(testNpart!=0){
                testNpart=testNpart/10;
                NpartDCount++;
            }
        
            String nextSupId="SUP-";
        
            Integer rounds=5-NpartDCount;
        
            while(rounds!=0){
                nextSupId=nextSupId+"0";
                rounds--;
            }
        
            nextSupId=nextSupId+""+Npart;
        
            return nextSupId;
        }else{
            return "SUP-00001";
        }
    }
    
}
