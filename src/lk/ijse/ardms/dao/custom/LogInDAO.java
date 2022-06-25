/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom;

import lk.ijse.ardms.dao.SuperDAO;

/**
 *
 * @author Thushara Supun
 */
public interface LogInDAO extends SuperDAO{
    
    public boolean isValid(String username, String password) throws Exception;
    
}
