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
public class CustomerManagementDTO {
    private String cusid;
    private String name;
    private String address_no;
    private String address_street;
    private String address_village;
    private String address_city;
    private String nic;
    private String tel_home;
    private String tel_mobile;
    private String email;

    public CustomerManagementDTO() {
    }

    public CustomerManagementDTO(String cusid, String name, String address_no, String address_street, String address_village, String address_city, String nic, String tel_home, String tel_mobile, String email) {
        this.cusid = cusid;
        this.name = name;
        this.address_no = address_no;
        this.address_street = address_street;
        this.address_village = address_village;
        this.address_city = address_city;
        this.nic = nic;
        this.tel_home = tel_home;
        this.tel_mobile = tel_mobile;
        this.email = email;
    }

    /**
     * @return the cusid
     */
    public String getCusid() {
        return cusid;
    }

    /**
     * @param cusid the cusid to set
     */
    public void setCusid(String cusid) {
        this.cusid = cusid;
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
     * @return the tel_home
     */
    public String getTel_home() {
        return tel_home;
    }

    /**
     * @param tel_home the tel_home to set
     */
    public void setTel_home(String tel_home) {
        this.tel_home = tel_home;
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

    @Override
    public String toString() {
        return "CustomerManagementDTO{" + "cusid=" + cusid + ", name=" + name + ", address_no=" + address_no + ", address_street=" + address_street + ", address_village=" + address_village + ", address_city=" + address_city + ", nic=" + nic + ", tel_home=" + tel_home + ", tel_mobile=" + tel_mobile + ", email=" + email + '}';
    }

    
    
    
}
