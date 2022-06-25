/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.business.custom.impl;

import lk.ijse.ardms.business.custom.CustomerReturnsBO;
import lk.ijse.ardms.dao.DAOFactory;
import lk.ijse.ardms.dao.custom.CustomerReturnsDAO;

/**
 *
 * @author Thushara Supun
 */
public class CustomerReturnsBOImpl implements CustomerReturnsBO{
    
    CustomerReturnsDAO customerReturnsDAO=(CustomerReturnsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMERRETURNS);
    
}
