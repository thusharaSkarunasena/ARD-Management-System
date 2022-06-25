/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.view.util.tblmodel;

/**
 *
 * @author Thushara Supun
 */
public class EmployeeManagementTM {
    
    private String empid;
    private String name;
    private String nic;
    private String employment;

    public EmployeeManagementTM() {
    }

    public EmployeeManagementTM(String empid, String name, String nic, String employment) {
        this.empid = empid;
        this.name = name;
        this.nic = nic;
        this.employment = employment;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the employment
     */
    public String getEmployment() {
        return employment;
    }

    /**
     * @param employment the employment to set
     */
    public void setEmployment(String employment) {
        this.employment = employment;
    }

    @Override
    public String toString() {
        return "EmployeeManagementTM{" + "empid=" + empid + ", name=" + name + ", nic=" + nic + ", employment=" + employment + '}';
    }
    
}
