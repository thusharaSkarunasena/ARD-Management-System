/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.LogInDAO;

/**
 *
 * @author Thushara Supun
 */
public class LogInDAOImpl implements LogInDAO{

    @Override
    public boolean isValid(String username, String password) throws Exception {
        if(username.isEmpty()){
            return false;
        }else{
            ResultSet rst=CrudUtill.executeQuery("SELECT * from userpass where username=?", username);
            
            String passwordInDB=null;
            
            if(rst.next()){
                passwordInDB=rst.getString(2);
                return passwordInDB.equals(password);
            }else{
                return false;
            }
        }
        
    }
    
}
