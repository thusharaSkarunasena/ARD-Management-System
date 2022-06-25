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
public class ItemManagementDTO {
    private String itemcode;
    private String catName;
    private String braName;
    private String supName;
    private String name;
    private String description;
    private String size;
    private double price;
    private int qty;
    private String other_details;

    public ItemManagementDTO() {
    }

    public ItemManagementDTO(String itemcode, String catName, String braName, String supName, String name, String description, String size, double price, int qty, String other_details) {
        this.itemcode = itemcode;
        this.catName = catName;
        this.braName = braName;
        this.supName = supName;
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.qty = qty;
        this.other_details = other_details;
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
     * @return the catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName the catName to set
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * @return the braName
     */
    public String getBraName() {
        return braName;
    }

    /**
     * @param braName the braName to set
     */
    public void setBraName(String braName) {
        this.braName = braName;
    }

    /**
     * @return the supName
     */
    public String getSupName() {
        return supName;
    }

    /**
     * @param supName the supName to set
     */
    public void setSupName(String supName) {
        this.supName = supName;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
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
     * @return the other_details
     */
    public String getOther_details() {
        return other_details;
    }

    /**
     * @param other_details the other_details to set
     */
    public void setOther_details(String other_details) {
        this.other_details = other_details;
    }

    @Override
    public String toString() {
        return "ItemManagementDTO{" + "itemcode=" + itemcode + ", catName=" + catName + ", braName=" + braName + ", supName=" + supName + ", name=" + name + ", description=" + description + ", size=" + size + ", price=" + price + ", qty=" + qty + ", other_details=" + other_details + '}';
    }

    
}
