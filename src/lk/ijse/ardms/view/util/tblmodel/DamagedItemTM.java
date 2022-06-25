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
public class DamagedItemTM {
    
    private String itemcode;
    private String Name;
    private String size;
    private String category;
    private int qty;
    private String details;

    public DamagedItemTM() {
    }

    public DamagedItemTM(String itemcode, String Name, String size, String category, int qty, String details) {
        this.itemcode = itemcode;
        this.Name = Name;
        this.size = size;
        this.category = category;
        this.qty = qty;
        this.details = details;
    }

    /**
     * @return the itemcode
     */
    public String getItemcode() {
        return itemcode;
    }

    /**
     * @param itemcode the itemcode to set
     */
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
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
        return "DamageItemTM{" + "itemcode=" + itemcode + ", Name=" + Name + ", size=" + size + ", category=" + category + ", qty=" + qty + ", details=" + details + '}';
    }
    
}
