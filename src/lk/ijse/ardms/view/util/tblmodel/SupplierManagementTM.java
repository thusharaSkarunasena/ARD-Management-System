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
public class SupplierManagementTM {
    private String supid;
    private String name;
    private String nic;
    private String company;

    public SupplierManagementTM() {
    }

    public SupplierManagementTM(String supid, String name, String nic, String company) {
        this.supid = supid;
        this.name = name;
        this.nic = nic;
        this.company = company;
    }

    /**
     * @return the supid
     */
    public String getSupid() {
        return supid;
    }

    /**
     * @param supid the supid to set
     */
    public void setSupid(String supid) {
        this.supid = supid;
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
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "SupplierManagementTM{" + "supid=" + supid + ", name=" + name + ", nic=" + nic + ", company=" + company + '}';
    }
    
}
