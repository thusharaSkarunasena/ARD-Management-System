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
public class BillPaymentsDTO {
    
    private String billPaymentNo;
    private String PaymentType;
    private double payble_total;

    public BillPaymentsDTO() {
    }

    public BillPaymentsDTO(String billPaymentNo, String PaymentType, double payble_total) {
        this.billPaymentNo = billPaymentNo;
        this.PaymentType = PaymentType;
        this.payble_total = payble_total;
    }

    /**
     * @return the billPaymentNo
     */
    public String getBillPaymentNo() {
        return billPaymentNo;
    }

    /**
     * @param billPaymentNo the billPaymentNo to set
     */
    public void setBillPaymentNo(String billPaymentNo) {
        this.billPaymentNo = billPaymentNo;
    }

    /**
     * @return the PaymentType
     */
    public String getPaymentType() {
        return PaymentType;
    }

    /**
     * @param PaymentType the PaymentType to set
     */
    public void setPaymentType(String PaymentType) {
        this.PaymentType = PaymentType;
    }

    /**
     * @return the payble_total
     */
    public double getPayble_total() {
        return payble_total;
    }

    /**
     * @param payble_total the payble_total to set
     */
    public void setPayble_total(double payble_total) {
        this.payble_total = payble_total;
    }

    @Override
    public String toString() {
        return "BillPaymentsDTO{" + "billPaymentNo=" + billPaymentNo + ", PaymentType=" + PaymentType + ", payble_total=" + payble_total + '}';
    }
    
}
