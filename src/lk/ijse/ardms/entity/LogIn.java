/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.entity;

/**
 *
 * @author Thushara Supun
 */
public class LogIn {
    
    private String username;
    private String password;
    private String empid;

    public LogIn() {
    }

    public LogIn(String username, String password, String empid) {
        this.username = username;
        this.password = password;
        this.empid = empid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the empid
     */
    public String getEmpid() {
        return empid;
    }

    /**
     * @param empid the empid to set
     */
    public void setEmpid(String empid) {
        this.empid = empid;
    }

    @Override
    public String toString() {
        return "LogIn{" + "username=" + username + ", password=" + password + ", empid=" + empid + '}';
    }
    
}
