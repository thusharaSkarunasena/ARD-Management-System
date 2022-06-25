/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom;

import lk.ijse.ardms.business.SuperBO;

/**
 *
 * @author Thushara Supun
 */
public interface LogInBO extends SuperBO{
    
    public boolean isValid(String usename,String password) throws Exception;
    
}
