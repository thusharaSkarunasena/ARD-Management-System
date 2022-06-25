/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import lk.ijse.ardms.business.custom.LogInBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.LogInDAO;

/**
 *
 * @author Thushara Supun
 */
public class LogInBOImpl implements LogInBO{
    
    LogInDAO logInDAO=(LogInDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LOGIN);

    @Override
    public boolean isValid(String usename, String password) throws Exception {
        
        return logInDAO.isValid(usename, password);
    }
    
}
