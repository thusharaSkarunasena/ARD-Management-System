/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.ItemManagementDAO;
import lk.ijse.ardms.entity.ItemBrand;
import lk.ijse.ardms.entity.ItemCategory;
import lk.ijse.ardms.entity.ItemManagement;
import lk.ijse.ardms.entity.SupplierManagement;

/**
 *
 * @author Thushara Supun
 */
public class ItemManagementDAOImpl implements ItemManagementDAO{
    
    @Override
    public ArrayList<ItemManagement> getAll() throws Exception {
        
        ResultSet rst=CrudUtill.executeQuery("SELECT * from item");
        ArrayList<ItemManagement> itemManagements=new ArrayList<>();
        
        String catName=null;
        String braName=null;
        String supName=null;
        
        while(rst.next()){
            itemManagements.add(new ItemManagement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getInt(9),
                    rst.getString(10)
            ));
        }
        
        ArrayList<ItemManagement> itemManagementsFinalArrayList=new ArrayList<>();
        
        for(ItemManagement itemManagement : itemManagements){
            
            ResultSet rst1=CrudUtill.executeQuery("SELECT name from category where catid=?", itemManagement.getCatName());
            while(rst1.next()){
                 catName=rst1.getString(1);
            }
            
            ResultSet rst2=CrudUtill.executeQuery("SELECT name from brand where braid=?", itemManagement.getBraName());
            while(rst2.next()){
                braName=rst2.getString(1);
            }
            
            ResultSet rst3=CrudUtill.executeQuery("SELECT name from supplier where supid=?", itemManagement.getSupName());
            while(rst3.next()){
                supName=rst3.getString(1);
            }
            
            itemManagementsFinalArrayList.add(new ItemManagement(
                    itemManagement.getItemcode(),
                    catName,
                    braName,
                    supName,
                    itemManagement.getName(),
                    itemManagement.getDescription(),
                    itemManagement.getSize(),
                    itemManagement.getPrice(),
                    itemManagement.getQty(),
                    itemManagement.getOther_details()
            ));
        }
        return itemManagementsFinalArrayList;
    }

    
    @Override
    public ItemManagement getOthers(String itemCode) throws Exception {
        
        ResultSet rst=CrudUtill.executeQuery("SELECT * from item where itemcode=?", itemCode);
        
        String catName=null;
        String braName=null;
        String supName=null;
        
        ItemManagement itemManagement=null;
        while(rst.next()){
            itemManagement=new ItemManagement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getInt(9),
                    rst.getString(10)
            );
        }
        ResultSet rst1=CrudUtill.executeQuery("SELECT name from category where catid=?", itemManagement.getCatName());
        while(rst1.next()){
            catName=rst1.getString(1);
        }
            
        ResultSet rst2=CrudUtill.executeQuery("SELECT name from brand where braid=?", itemManagement.getBraName());
        while(rst2.next()){
            braName=rst2.getString(1);
        }
            
        ResultSet rst3=CrudUtill.executeQuery("SELECT name from supplier where supid=?", itemManagement.getSupName());
        while(rst3.next()){
            supName=rst3.getString(1);
        }
        
        ItemManagement itemManagementFinal=new ItemManagement(itemManagement.getItemcode(), catName, braName, supName, itemManagement.getName(), itemManagement.getDescription(), itemManagement.getSize(), itemManagement.getPrice(), itemManagement.getQty(), itemManagement.getOther_details());
        
        return itemManagementFinal;
    }
    
    
    @Override
    public ItemManagement get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public boolean save(ItemManagement entity) throws Exception {
        
        ResultSet rst1=CrudUtill.executeQuery("SELECT catid from category where name=?", entity.getCatName());
        String catid=null;
        while(rst1.next()){
            catid=rst1.getString(1 );
        }
        
        ResultSet rst2=CrudUtill.executeQuery("SELECT braid from brand where name=?", entity.getBraName());
        String braid=null;
        while(rst2.next()){
            braid=rst2.getString(1);
        }
        
        ResultSet rst3=CrudUtill.executeQuery("SELECT supid from supplier where name=?", entity.getSupName());
        String supid=null;
        while(rst3.next()){
            supid=rst3.getString(1);
        }
        
        return CrudUtill.executeUpdate("INSERT INTO item VALUES(?,?,?,?,?,?,?,?,?,?)", entity.getItemcode(), catid, braid, supid, entity.getName(), entity.getDescription(), entity.getSize(), entity.getPrice(), entity.getQty(), entity.getOther_details());
    }

    
    @Override
    public boolean update(ItemManagement entity) throws Exception {
        ResultSet rst1=CrudUtill.executeQuery("SELECT catid from category where name=?", entity.getCatName());
        String catid=null;
        while(rst1.next()){
            catid=rst1.getString(1 );
        }
        
        ResultSet rst2=CrudUtill.executeQuery("SELECT braid from brand where name=?", entity.getBraName());
        String braid=null;
        while(rst2.next()){
            braid=rst2.getString(1);
        }
        
        ResultSet rst3=CrudUtill.executeQuery("SELECT supid from supplier where name=?", entity.getSupName());
        String supid=null;
        while(rst3.next()){
            supid=rst3.getString(1);
        }
        
        return CrudUtill.executeUpdate("UPDATE item set catid=?, braid=?, supid=?, name=?, description=?, size=?, price=?, qty=?, other_details=? where itemcode=?", catid, braid, supid, entity.getName(), entity.getDescription(), entity.getSize(), entity.getPrice(), entity.getQty(), entity.getOther_details(), entity.getItemcode());
    }

    
    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtill.executeUpdate("DELETE from item where itemcode=?", id);
    }
    
    
    @Override
    public ArrayList<ItemManagement> searchItems(String searchText) throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from item where itemcode like '%"+searchText+"%' or name like '%"+searchText+"%' ");
        ArrayList<ItemManagement> itemManagements=new ArrayList<>();
        
        String catName=null;
        String braName=null;
        String supName=null;
        
        while(rst.next()){
            itemManagements.add(new ItemManagement(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getDouble(8),
                    rst.getInt(9),
                    rst.getString(10)
            ));
        }
        
        ArrayList<ItemManagement> itemManagementsFinalArrayList=new ArrayList<>();
        
        for(ItemManagement itemManagement : itemManagements){
            
            ResultSet rst1=CrudUtill.executeQuery("SELECT name from category where catid=?", itemManagement.getCatName());
            while(rst1.next()){
                 catName=rst1.getString(1);
            }
            
            ResultSet rst2=CrudUtill.executeQuery("SELECT name from brand where braid=?", itemManagement.getBraName());
            while(rst2.next()){
                braName=rst2.getString(1);
            }
            
            ResultSet rst3=CrudUtill.executeQuery("SELECT name from supplier where supid=?", itemManagement.getSupName());
            while(rst3.next()){
                supName=rst3.getString(1);
            }
            
            itemManagementsFinalArrayList.add(new ItemManagement(
                    itemManagement.getItemcode(),
                    catName,
                    braName,
                    supName,
                    itemManagement.getName(),
                    itemManagement.getDescription(),
                    itemManagement.getSize(),
                    itemManagement.getPrice(),
                    itemManagement.getQty(),
                    itemManagement.getOther_details()
            ));
        }
        return itemManagementsFinalArrayList;
    }

    @Override
    public ArrayList<ItemCategory> loadCategoryComboBox() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from category");
        ArrayList<ItemCategory> itemCategorys=new ArrayList<>();
        
        while(rst.next()){
            itemCategorys.add(new ItemCategory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        
        return itemCategorys;
    }

    @Override
    public ArrayList<ItemBrand> loadBrandComboBox() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from brand");
        ArrayList<ItemBrand> itemBrands=new ArrayList<>();
        
        while(rst.next()){
            itemBrands.add(new ItemBrand(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return itemBrands;
    }

    @Override
    public ArrayList<SupplierManagement> loadSupplierComboBox() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from supplier");
        ArrayList<SupplierManagement> supplierManagements=new ArrayList<>();
        
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
    public String generateItemCode() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("select itemcode from item order by 1 desc limit 1;");
        String lastItemCode=null;
        Integer NpartDCount=0;
        
        if(rst.next()){
            lastItemCode=rst.getString(1);
            
            String[] output = lastItemCode.split("-");
        
            Integer Npart=Integer.parseInt(output[1]);
            Npart=Npart+1;
            Integer testNpart=Npart;
        
        
            while(testNpart!=0){
                testNpart=testNpart/10;
                NpartDCount++;
            }
        
            String nextItemCode="I-";
        
            Integer rounds=8-NpartDCount;
        
            while(rounds!=0){
                nextItemCode=nextItemCode+"0";
                rounds--;
            }
        
            nextItemCode=nextItemCode+""+Npart;
        
            return nextItemCode;
        }else{
            return "I-00000001";
        }
    }
    
}
