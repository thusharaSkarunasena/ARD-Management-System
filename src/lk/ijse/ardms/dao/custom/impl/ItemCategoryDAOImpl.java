/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.ItemCategoryDAO;
import lk.ijse.ardms.entity.ItemCategory;

/**
 *
 * @author Thushara Supun
 */
public class ItemCategoryDAOImpl implements ItemCategoryDAO{
    
    @Override
    public ArrayList<ItemCategory> getAll() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from category");
        ArrayList<ItemCategory> itemCategorys = new ArrayList<>();
        while(rst.next()){
            itemCategorys.add(new ItemCategory(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        
        }
        
        return itemCategorys;
    }

    @Override
    public ItemCategory get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(ItemCategory entity) throws Exception {
        return CrudUtill.executeUpdate("INSERT INTO category values(?,?,?,?)", entity.getCatid(),entity.getName(),entity.getDescription(),entity.getOther_details());
    }

    @Override
    public boolean update(ItemCategory entity) throws Exception {
        return CrudUtill.executeUpdate("UPDATE category set name=?, description=?, other_details=? where catid=?", entity.getName(),entity.getDescription(),entity.getOther_details(),entity.getCatid());
    }

    @Override
    public boolean delete(String catid) throws Exception {
        return CrudUtill.executeUpdate("DELETE from category where catid=?", catid);
    }
    
    @Override
    public ArrayList<ItemCategory> search(String searchText) throws Exception {
        ArrayList<ItemCategory> searchCategorys = new ArrayList<>();
        ResultSet rst = CrudUtill.executeQuery("SELECT * from category where catid like '%"+searchText+"%' or name like '%"+searchText+"%' or description like '%"+searchText+"%' ");
        
        while (rst.next()) {
            ItemCategory itemCategory = new ItemCategory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
            searchCategorys.add(itemCategory);
        }
        return searchCategorys;
    }

    @Override
    public String generateItemCategoryId() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("select catid from category order by 1 desc limit 1;");
        String lastCatId=null;
        Integer NpartDCount=0;
        
        if(rst.next()){
            lastCatId=rst.getString(1);
            
            String[] output = lastCatId.split("-");
        
            Integer Npart=Integer.parseInt(output[1]);
            Npart=Npart+1;
            Integer testNpart=Npart;
        
        
            while(testNpart!=0){
                testNpart=testNpart/10;
                NpartDCount++;
            }
        
            String nextCatId="CAT-";
        
            Integer rounds=5-NpartDCount;
        
            while(rounds!=0){
                nextCatId=nextCatId+"0";
                rounds--;
            }
        
            nextCatId=nextCatId+""+Npart;
        
            return nextCatId;
        }else{
            return "CAT-00001";
        }
    }
    
}
