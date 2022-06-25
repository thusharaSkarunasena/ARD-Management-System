/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.ijse.ardms.dao.CrudUtill;
import lk.ijse.ardms.dao.custom.EmployeeManagementDAO;
import lk.ijse.ardms.entity.EmployeeManagement;

/**
 *
 * @author Thushara Supun
 */
    public class EmployeeManagementDAOImpl implements EmployeeManagementDAO{

    @Override
    public ArrayList<EmployeeManagement> getAll() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from employee");
        ArrayList<EmployeeManagement> employeeManagements=new ArrayList<>();
        
        while(rst.next()){
            employeeManagements.add(new EmployeeManagement(
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
                    rst.getString(11)
            ));
        }
        return employeeManagements;
    }

    @Override
    public EmployeeManagement getOthers(String empid) throws Exception {
        ResultSet rst=CrudUtill.executeQuery("SELECT * from employee where empid=?", empid);
        
        while(rst.next()){
          return new EmployeeManagement(
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
                    rst.getString(11)
            );
        }
        return null;
    }
    
    @Override
    public EmployeeManagement get(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(EmployeeManagement entity) throws Exception {
        return CrudUtill.executeUpdate("INSERT INTO employee values(?,?,?,?,?,?,?,?,?,?,?)", entity.getEmpid(), entity.getName(), entity.getEmployment(), entity.getAddress_no(), entity.getAddress_street(), entity.getAddress_village(), entity.getAddress_city(), entity.getNic(), entity.getTel_home(), entity.getTel_mobile(), entity.getOtherDetails());
    }

    @Override
    public boolean update(EmployeeManagement entity) throws Exception {
        return CrudUtill.executeUpdate("UPDATE employee set name=?, employment=?, address_no=?, address_street=?, address_village=?, address_city=?, nic=?, tel_home=?, tel_mobile=?, other=? where empid=?", entity.getName(), entity.getEmployment(), entity.getAddress_no(), entity.getAddress_street(), entity.getAddress_village(), entity.getAddress_city(), entity.getNic(), entity.getTel_home(), entity.getTel_mobile(), entity.getOtherDetails(), entity.getEmpid());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtill.executeUpdate("DELETE from employee where empid=?", id);
    }
    
    @Override
    public ArrayList<EmployeeManagement> searchEmployee(String searchText) throws Exception {
        ArrayList<EmployeeManagement> searchEmployees = new ArrayList<>();
        ResultSet rst = CrudUtill.executeQuery("SELECT * from employee where empid like '%"+searchText+"%' or name like '%"+searchText+"%' or nic like '%"+searchText+"%' or employment like '%"+searchText+"%' ");
        
        while (rst.next()) {
            EmployeeManagement employeeManagement = new EmployeeManagement(
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
                    rst.getString(10));
            searchEmployees.add(employeeManagement);
        }
        return searchEmployees;
    }

    @Override
    public String generateEmployeeId() throws Exception {
        ResultSet rst=CrudUtill.executeQuery("select empid from employee order by 1 desc limit 1;");
        String lastEmpId=null;
        Integer NpartDCount=0;
        
        if(rst.next()){
            lastEmpId=rst.getString(1);
            
            String[] output = lastEmpId.split("-");
        
            Integer Npart=Integer.parseInt(output[1]);
            Npart=Npart+1;
            Integer testNpart=Npart;
        
        
            while(testNpart!=0){
                testNpart=testNpart/10;
                NpartDCount++;
            }
        
            String nextEmpId="EMP-";
        
            Integer rounds=5-NpartDCount;
        
            while(rounds!=0){
                nextEmpId=nextEmpId+"0";
                rounds--;
            }
        
            nextEmpId=nextEmpId+""+Npart;
        
            return nextEmpId;
        }else{
            return "EMP-00001";
        }
    }
    
}
