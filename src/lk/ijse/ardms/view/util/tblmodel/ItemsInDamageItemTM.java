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
public class ItemsInDamageItemTM {
    
    private String itemcode;
    private String name;

    public ItemsInDamageItemTM() {
    }

    public ItemsInDamageItemTM(String itemcode, String name) {
        this.itemcode = itemcode;
        this.name = name;
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

    @Override
    public String toString() {
        return "itemsInDamageItem{" + "itemcode=" + itemcode + ", name=" + name + '}';
    }
    
}
