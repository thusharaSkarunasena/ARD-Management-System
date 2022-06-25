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
public class DamagedItem {
    
    private String itemcode;
    private String name;
    private String size;
    private String category;
    private int qty;
    private String details;

    public DamagedItem() {
    }

    public DamagedItem(String itemcode, String name, String size, String category, int qty, String details) {
        this.itemcode = itemcode;
        this.name = name;
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
        return "DamagedItem{" + "itemcode=" + itemcode + ", name=" + name + ", size=" + size + ", category=" + category + ", qty=" + qty + ", details=" + details + '}';
    }
    
}
