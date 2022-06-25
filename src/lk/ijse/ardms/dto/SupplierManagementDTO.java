/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dto;

/**
 *
 * @author Thushara Supun
 */
public class SupplierManagementDTO {
    private String supid;
    private String name;
    private String company;
    private String address_no;
    private String address_street;
    private String address_village;
    private String address_city;
    private String nic;
    private String tel_office;
    private String tel_mobile;
    private String email;
    private String otherDetails;

    public SupplierManagementDTO() {
    }

    public SupplierManagementDTO(String supid, String name, String company, String address_no, String address_street, String address_village, String address_city, String nic, String tel_office, String tel_mobile, String email, String otherDetails) {
        this.supid = supid;
        this.name = name;
        this.company = company;
        this.address_no = address_no;
        this.address_street = address_street;
        this.address_village = address_village;
        this.address_city = address_city;
        this.nic = nic;
        this.tel_office = tel_office;
        this.tel_mobile = tel_mobile;
        this.email = email;
        this.otherDetails = otherDetails;
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

    /**
     * @return the address_no
     */
    public String getAddress_no() {
        return address_no;
    }

    /**
     * @param address_no the address_no to set
     */
    public void setAddress_no(String address_no) {
        this.address_no = address_no;
    }

    /**
     * @return the address_street
     */
    public String getAddress_street() {
        return address_street;
    }

    /**
     * @param address_street the address_street to set
     */
    public void setAddress_street(String address_street) {
        this.address_street = address_street;
    }

    /**
     * @return the address_village
     */
    public String getAddress_village() {
        return address_village;
    }

    /**
     * @param address_village the address_village to set
     */
    public void setAddress_village(String address_village) {
        this.address_village = address_village;
    }

    /**
     * @return the address_city
     */
    public String getAddress_city() {
        return address_city;
    }

    /**
     * @param address_city the address_city to set
     */
    public void setAddress_city(String address_city) {
        this.address_city = address_city;
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
     * @return the tel_office
     */
    public String getTel_office() {
        return tel_office;
    }

    /**
     * @param tel_office the tel_office to set
     */
    public void setTel_office(String tel_office) {
        this.tel_office = tel_office;
    }

    /**
     * @return the tel_mobile
     */
    public String getTel_mobile() {
        return tel_mobile;
    }

    /**
     * @param tel_mobile the tel_mobile to set
     */
    public void setTel_mobile(String tel_mobile) {
        this.tel_mobile = tel_mobile;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the otherDetails
     */
    public String getOtherDetails() {
        return otherDetails;
    }

    /**
     * @param otherDetails the otherDetails to set
     */
    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "SupplierManagementDTO{" + "supid=" + supid + ", name=" + name + ", company=" + company + ", address_no=" + address_no + ", address_street=" + address_street + ", address_village=" + address_village + ", address_city=" + address_city + ", nic=" + nic + ", tel_office=" + tel_office + ", tel_mobile=" + tel_mobile + ", email=" + email + ", otherDetails=" + otherDetails + '}';
    }
    
}
