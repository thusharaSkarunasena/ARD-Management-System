/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.entity.DamagedItem;
import lk.ijse.ardms.dao.custom.DamagedItemDAO;
import lk.ijse.ardms.entity.ItemManagement;

/**
 *
 * @author Thushara Supun
 */
public class DamagedItemDAOImpl implements DamagedItemDAO{

    
    @Override
    public ArrayList<DamagedItem> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DamagedItem get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(DamagedItem entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(DamagedItem entity) throws Exception {
        ResultSet rst1=CrudUtill.executeQuery("SELECT * from damaged_item where itemcodeInItem=?", entity.getItemcode());
        
        String name=null;
        String size=null;
        String category=null;
        Integer qty=null;
        
        while(rst1.next()){
            name=rst1.getString(2);
            size=rst1.getString(3);
            category=rst1.getString(4);
            qty=rst1.getInt(5);
        }
        
        ResultSet rst2=CrudUtill.executeQuery("SELECT * from item where itemcode=?", entity.getItemcode());
        ItemManagement itemManagement=null;
        
        while(rst2.next()){
            itemManagement=new ItemManagement(
                    rst2.getString(1),
                    rst2.getString(2),
                    rst2.getString(3),
                    rst2.getString(4),
                    rst2.getString(5),
                    rst2.getString(6),
                    rst2.getString(7),
                    rst2.getDouble(8),
                    rst2.getInt(9),
                    rst2.getString(10)   
            );
        }
        
        boolean result;
        int newQtyOnHand=itemManagement.getQty()-(entity.getQty()-qty);
        if(newQtyOnHand>=0){
            boolean b1=CrudUtill.executeUpdate("UPDATE item set catid=?, braid=?, supid=?, name=?, description=?, size=?, price=?, qty=?, other_details=? where itemcode=?", itemManagement.getCatName(), itemManagement.getBraName(), itemManagement.getSupName(), itemManagement.getName(), itemManagement.getDescription(), itemManagement.getSize(), itemManagement.getPrice(), newQtyOnHand, itemManagement.getOther_details(), entity.getItemcode());
            
            result=CrudUtill.executeUpdate("UPDATE damaged_item set itemName=?, size=?, category=?, qty=?, details=? where itemcodeInItem=?", name, size, category, entity.getQty(), entity.getDetails(), entity.getItemcode());
        }else{
            result=false;
        }
        return result;
        
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<DamagedItem> getAllDamagedItems() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from damaged_item");
        
        ArrayList<DamagedItem> damagedItems = new ArrayList<>();
        
        while(rst.next()){
            damagedItems.add(new DamagedItem(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6)
            ));
        }
        return damagedItems;
    }

    @Override
    public boolean add(DamagedItem damagedItem) throws Exception {
        
        ResultSet rst1=CrudUtill.executeQuery("SELECT * from item where itemcode=?", damagedItem.getItemcode());
        ItemManagement itemManagement=null;
        
        String categoryName=null;
        
        while(rst1.next()){
            itemManagement=new ItemManagement(
                    rst1.getString(1),
                    rst1.getString(2),
                    rst1.getString(3),
                    rst1.getString(4),
                    rst1.getString(5),
                    rst1.getString(6),
                    rst1.getString(7),
                    rst1.getDouble(8),
                    rst1.getInt(9),
                    rst1.getString(10)   
            );
        }
        
        ResultSet rst2=CrudUtill.executeQuery("SELECT name from category where catid=?", itemManagement.getCatName());
        while(rst2.next()){
            categoryName=rst2.getString(1);
        }
        
        boolean result;
        int newQtyOnHand=itemManagement.getQty()-damagedItem.getQty();
        if(newQtyOnHand>=0){
            boolean b1=CrudUtill.executeUpdate("UPDATE item set catid=?, braid=?, supid=?, name=?, description=?, size=?, price=?, qty=?, other_details=? where itemcode=?", itemManagement.getCatName(), itemManagement.getBraName(), itemManagement.getSupName(), itemManagement.getName(), itemManagement.getDescription(), itemManagement.getSize(), itemManagement.getPrice(), newQtyOnHand, itemManagement.getOther_details(), damagedItem.getItemcode());
            
            result=CrudUtill.executeUpdate("INSERT INTO damaged_item VALUES(?,?,?,?,?,?)", damagedItem.getItemcode(), itemManagement.getName(), itemManagement.getSize(), categoryName, damagedItem.getQty(), damagedItem.getDetails());
        }else{
            result=false;
        }
        return result;
    }

    @Override
    public boolean remove(String itemcode) throws Exception {
        return CrudUtill.executeUpdate("DELETE FROM damaged_item where itemcodeInItem=?", itemcode);
    }

    @Override
    public ArrayList<DamagedItem> searchDamagedItems(String searchText) throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from damaged_item where itemcodeInItem like '%"+searchText+"%' or itemName like '%"+searchText+"%' or details like '%"+searchText+"%'");
        
        ArrayList<DamagedItem> damagedItems=new ArrayList<>();
        
        while(rst.next()){
            damagedItems.add(new DamagedItem(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6)      
            ));
        }
        return damagedItems;
    }

    @Override
    public boolean restore(String itemcode) throws Exception {
        ResultSet rst1=CrudUtill.executeQuery("SELECT * from damaged_item where itemcodeInItem=?", itemcode);
        Integer qty=null;
        
        while(rst1.next()){
            qty=rst1.getInt(5);
        }
        
        ResultSet rst2=CrudUtill.executeQuery("SELECT * from item where itemcode=?", itemcode);
        ItemManagement itemManagement=null;
        
        while(rst2.next()){
            itemManagement=new ItemManagement(
                    rst2.getString(1),
                    rst2.getString(2),
                    rst2.getString(3),
                    rst2.getString(4),
                    rst2.getString(5),
                    rst2.getString(6),
                    rst2.getString(7),
                    rst2.getDouble(8),
                    rst2.getInt(9),
                    rst2.getString(10)   
            );
        }
        
        boolean b1;
        boolean b2;
            
        int newQtyOnHand=itemManagement.getQty()+qty;
        
        b1=CrudUtill.executeUpdate("DELETE from damaged_item where itemcodeInItem=?", itemcode);
        b2=CrudUtill.executeUpdate("UPDATE item set catid=?, braid=?, supid=?, name=?, description=?, size=?, price=?, qty=?, other_details=? where itemcode=?", itemManagement.getCatName(), itemManagement.getBraName(), itemManagement.getSupName(), itemManagement.getName(), itemManagement.getDescription(), itemManagement.getSize(), itemManagement.getPrice(), newQtyOnHand, itemManagement.getOther_details(), itemcode);
           
        return (b1 & b2);
    }
}

    

