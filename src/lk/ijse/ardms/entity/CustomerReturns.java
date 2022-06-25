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
public class CustomerReturns {
    private String CRNo;
    private String refNo;
    private String billDate;
    private String status;
    private String customerName;
    private String itemcode1;
    private int qty1;
    private String itemcode2;
    private int qty2;
    private String itemcode3;
    private int qty3;
    private String itemcode4;
    private int qty4;
    private String itemcode5;
    private int qty5;
    private String details;
    private double releasedAmount;

    public CustomerReturns() {
    }

    public CustomerReturns(String CRNo, String refNo, String billDate, String status, String customerName, String itemcode1, int qty1, String itemcode2, int qty2, String itemcode3, int qty3, String itemcode4, int qty4, String itemcode5, int qty5, String details, double releasedAmount) {
        this.CRNo = CRNo;
        this.refNo = refNo;
        this.billDate = billDate;
        this.status = status;
        this.customerName = customerName;
        this.itemcode1 = itemcode1;
        this.qty1 = qty1;
        this.itemcode2 = itemcode2;
        this.qty2 = qty2;
        this.itemcode3 = itemcode3;
        this.qty3 = qty3;
        this.itemcode4 = itemcode4;
        this.qty4 = qty4;
        this.itemcode5 = itemcode5;
        this.qty5 = qty5;
        this.details = details;
        this.releasedAmount = releasedAmount;
    }

    /**
     * @return the CRNo
     */
    public String getCRNo() {
        return CRNo;
    }

    /**
     * @param CRNo the CRNo to set
     */
    public void setCRNo(String CRNo) {
        this.CRNo = CRNo;
    }

    /**
     * @return the refNo
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * @param refNo the refNo to set
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    /**
     * @return the billDate
     */
    public String getBillDate() {
        return billDate;
    }

    /**
     * @param billDate the billDate to set
     */
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the itemcode1
     */
    public String getItemcode1() {
        return itemcode1;
    }

    /**
     * @param itemcode1 the itemcode1 to set
     */
    public void setItemcode1(String itemcode1) {
        this.itemcode1 = itemcode1;
    }

    /**
     * @return the qty1
     */
    public int getQty1() {
        return qty1;
    }

    /**
     * @param qty1 the qty1 to set
     */
    public void setQty1(int qty1) {
        this.qty1 = qty1;
    }

    /**
     * @return the itemcode2
     */
    public String getItemcode2() {
        return itemcode2;
    }

    /**
     * @param itemcode2 the itemcode2 to set
     */
    public void setItemcode2(String itemcode2) {
        this.itemcode2 = itemcode2;
    }

    /**
     * @return the qty2
     */
    public int getQty2() {
        return qty2;
    }

    /**
     * @param qty2 the qty2 to set
     */
    public void setQty2(int qty2) {
        this.qty2 = qty2;
    }

    /**
     * @return the itemcode3
     */
    public String getItemcode3() {
        return itemcode3;
    }

    /**
     * @param itemcode3 the itemcode3 to set
     */
    public void setItemcode3(String itemcode3) {
        this.itemcode3 = itemcode3;
    }

    /**
     * @return the qty3
     */
    public int getQty3() {
        return qty3;
    }

    /**
     * @param qty3 the qty3 to set
     */
    public void setQty3(int qty3) {
        this.qty3 = qty3;
    }

    /**
     * @return the itemcode4
     */
    public String getItemcode4() {
        return itemcode4;
    }

    /**
     * @param itemcode4 the itemcode4 to set
     */
    public void setItemcode4(String itemcode4) {
        this.itemcode4 = itemcode4;
    }

    /**
     * @return the qty4
     */
    public int getQty4() {
        return qty4;
    }

    /**
     * @param qty4 the qty4 to set
     */
    public void setQty4(int qty4) {
        this.qty4 = qty4;
    }

    /**
     * @return the itemcode5
     */
    public String getItemcode5() {
        return itemcode5;
    }

    /**
     * @param itemcode5 the itemcode5 to set
     */
    public void setItemcode5(String itemcode5) {
        this.itemcode5 = itemcode5;
    }

    /**
     * @return the qty5
     */
    public int getQty5() {
        return qty5;
    }

    /**
     * @param qty5 the qty5 to set
     */
    public void setQty5(int qty5) {
        this.qty5 = qty5;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return the releasedAmount
     */
    public double getReleasedAmount() {
        return releasedAmount;
    }

    /**
     * @param releasedAmount the releasedAmount to set
     */
    public void setReleasedAmount(double releasedAmount) {
        this.releasedAmount = releasedAmount;
    }

    @Override
    public String toString() {
        return "CustomerReturns{" + "CRNo=" + CRNo + ", refNo=" + refNo + ", billDate=" + billDate + ", status=" + status + ", customerName=" + customerName + ", itemcode1=" + itemcode1 + ", qty1=" + qty1 + ", itemcode2=" + itemcode2 + ", qty2=" + qty2 + ", itemcode3=" + itemcode3 + ", qty3=" + qty3 + ", itemcode4=" + itemcode4 + ", qty4=" + qty4 + ", itemcode5=" + itemcode5 + ", qty5=" + qty5 + ", details=" + details + ", releasedAmount=" + releasedAmount + '}';
    }
    
}
