/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.ItemBrandDAO;
import lk.ijse.ardms.entity.ItemBrand;

/**
 *
 * @author Thushara Supun
 */
public class ItemBrandDAOImpl implements ItemBrandDAO{

    @Override
    public ArrayList<ItemBrand> getAll() throws Exception {
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
    public ItemBrand get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(ItemBrand entity) throws Exception {
        return CrudUtill.executeUpdate("INSERT INTO brand value(?,?,?,?)", entity.getBraId(), entity.getName(), entity.getDescription(), entity.getOtherDetails());
    }

    @Override
    public boolean update(ItemBrand entity) throws Exception {
        return CrudUtill.executeUpdate("UPDATE brand set name=?, description=?, other_details=? where braid=?", entity.getName(), entity.getDescription(), entity.getOtherDetails(), entity.getBraId());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtill.executeUpdate("DELETE from brand where braid=?", id);
    }
    
    @Override
    public ArrayList<ItemBrand> searchBrand(String searchText) throws Exception {
        ArrayList<ItemBrand> searchBrands = new ArrayList<>();
        ResultSet rst = CrudUtill.executeQuery("SELECT * from brand where braid like '%"+searchText+"%' or name like '%"+searchText+"%' or description like '%"+searchText+"%' ");
        
        while (rst.next()) {
            ItemBrand itemBrand = new ItemBrand(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4));
            searchBrands.add(itemBrand);
        }
        return searchBrands;
    }

    @Override
    public String generateItemBrandId() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("select braid from brand order by 1 desc limit 1;");
        String lastBraId=null;
        Integer NpartDCount=0;
        
        if(rst.next()){
            lastBraId=rst.getString(1);
            
            String[] output = lastBraId.split("-");
        
            Integer Npart=Integer.parseInt(output[1]);
            Npart=Npart+1;
            Integer testNpart=Npart;
        
        
            while(testNpart!=0){
                testNpart=testNpart/10;
                NpartDCount++;
            }
        
            String nextBraId="BRA-";
        
            Integer rounds=5-NpartDCount;
        
            while(rounds!=0){
                nextBraId=nextBraId+"0";
                rounds--;
            }
        
            nextBraId=nextBraId+""+Npart;
        
            return nextBraId;
        }else{
            return "BRA-00001";
        }
    }
    
}
