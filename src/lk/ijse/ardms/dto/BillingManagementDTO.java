/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.ardms.dto;

import java.util.ArrayList;
import lk.ijse.ardms.view.util.tblmodel.BillingManagementTM;

/**
 *
 * @author Thushara Supun
 */
public class BillingManagementDTO {
    private String refno;
    private String billno;
    private String cusid;
    private String date;
    private String empid;
    
    private ArrayList<BillingManagementTM> billingManagementTMs;
    
    private double tot_Amount;
    private double discount;
    private double net_Amount;

    public BillingManagementDTO() {
    }

    public BillingManagementDTO(String refno, String billno, String cusid, String date, String empid, ArrayList<BillingManagementTM> billingManagementTMs, double tot_Amount, double discount, double net_Amount) {
        this.refno = refno;
        this.billno = billno;
        this.cusid = cusid;
        this.date = date;
        this.empid = empid;
        this.billingManagementTMs = billingManagementTMs;
        this.tot_Amount = tot_Amount;
        this.discount = discount;
        this.net_Amount = net_Amount;
    }

    /**
     * @return the refno
     */
    public String getRefno() {
        return refno;
    }

    /**
     * @param refno the refno to set
     */
    public void setRefno(String refno) {
        this.refno = refno;
    }

    /**
     * @return the billno
     */
    public String getBillno() {
        return billno;
    }

    /**
     * @param billno the billno to set
     */
    public void setBillno(String billno) {
        this.billno = billno;
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
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
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
     * @return the billingManagementTMs
     */
    public ArrayList<BillingManagementTM> getBillingManagementTMs() {
        return billingManagementTMs;
    }

    /**
     * @param billingManagementTMs the billingManagementTMs to set
     */
    public void setBillingManagementTMs(ArrayList<BillingManagementTM> billingManagementTMs) {
        this.billingManagementTMs = billingManagementTMs;
    }

    /**
     * @return the tot_Amount
     */
    public double getTot_Amount() {
        return tot_Amount;
    }

    /**
     * @param tot_Amount the tot_Amount to set
     */
    public void setTot_Amount(double tot_Amount) {
        this.tot_Amount = tot_Amount;
    }

    /**
     * @return the discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * @return the net_Amount
     */
    public double getNet_Amount() {
        return net_Amount;
    }

    /**
     * @param net_Amount the net_Amount to set
     */
    public void setNet_Amount(double net_Amount) {
        this.net_Amount = net_Amount;
    }

    @Override
    public String toString() {
        return "BillingManagementDTO{" + "refno=" + refno + ", billno=" + billno + ", cusid=" + cusid + ", date=" + date + ", empid=" + empid + ", billingManagementTMs=" + billingManagementTMs + ", tot_Amount=" + tot_Amount + ", discount=" + discount + ", net_Amount=" + net_Amount + '}';
    }
    
}
