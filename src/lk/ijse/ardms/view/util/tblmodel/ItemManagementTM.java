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
public class ItemManagementTM {
    
    private String itemcode;
    private String name;
    private String size;
    private String catagory;
    private double price;
    private int qty;

    public ItemManagementTM() {
    }

    public ItemManagementTM(String itemcode, String name, String size, String catagory, double price, int qty) {
        this.itemcode = itemcode;
        this.name = name;
        this.size = size;
        this.catagory = catagory;
        this.price = price;
        this.qty = qty;
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
     * @return the catagory
     */
    public String getCatagory() {
        return catagory;
    }

    /**
     * @param catagory the catagory to set
     */
    public void setCatagory(String catagory) {
        this.catagory = catagory;
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

    @Override
    public String toString() {
        return "ItemManagementTM{" + "itemcode=" + itemcode + ", name=" + name + ", size=" + size + ", catagory=" + catagory + ", price=" + price + ", qty=" + qty + '}';
    }
    
    
}
