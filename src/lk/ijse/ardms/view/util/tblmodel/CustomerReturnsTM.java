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
public class CustomerReturnsTM {
    private String CRNo;
    private String billRefNo;
    private String customerName;
    private String returnedItems;
    private String details;

    public CustomerReturnsTM() {
    }

    public CustomerReturnsTM(String CRNo, String billRefNo, String customerName, String returnedItems, String details) {
        this.CRNo = CRNo;
        this.billRefNo = billRefNo;
        this.customerName = customerName;
        this.returnedItems = returnedItems;
        this.details = details;
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
     * @return the billRefNo
     */
    public String getBillRefNo() {
        return billRefNo;
    }

    /**
     * @param billRefNo the billRefNo to set
     */
    public void setBillRefNo(String billRefNo) {
        this.billRefNo = billRefNo;
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
     * @return the returnedItems
     */
    public String getReturnedItems() {
        return returnedItems;
    }

    /**
     * @param returnedItems the returnedItems to set
     */
    public void setReturnedItems(String returnedItems) {
        this.returnedItems = returnedItems;
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

    @Override
    public String toString() {
        return "CustomerReturnsTM{" + "CRNo=" + CRNo + ", billRefNo=" + billRefNo + ", customerName=" + customerName + ", returnedItems=" + returnedItems + ", details=" + details + '}';
    }
    
}
