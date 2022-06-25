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
public class ItemCategory {
    private String catid;
    private String name;
    private String description;
    private String other_details;

    public ItemCategory() {
    }

    public ItemCategory(String catid, String name, String description, String other_details) {
        this.catid = catid;
        this.name = name;
        this.description = description;
        this.other_details = other_details;
    }

    /**
     * @return the catid
     */
    public String getCatid() {
        return catid;
    }

    /**
     * @param catid the catid to set
     */
    public void setCatid(String catid) {
        this.catid = catid;
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
        return "ItemCategory{" + "catid=" + catid + ", name=" + name + ", description=" + description + ", other_details=" + other_details + '}';
    }
    
    
}
