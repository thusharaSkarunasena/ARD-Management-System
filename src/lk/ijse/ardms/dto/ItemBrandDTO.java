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
public class ItemBrandDTO {
    private String braId;
    private String name;
    private String description;
    private String otherDetails;

    public ItemBrandDTO() {
    }

    public ItemBrandDTO(String braId, String name, String description, String otherDetails) {
        this.braId = braId;
        this.name = name;
        this.description = description;
        this.otherDetails = otherDetails;
    }

    /**
     * @return the braId
     */
    public String getBraId() {
        return braId;
    }

    /**
     * @param braId the braId to set
     */
    public void setBraId(String braId) {
        this.braId = braId;
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
     * @return the otherDetails
     */
    public String getOtherDetails() {
        return otherDetails;
    }

    /**
     * @param otherDetails the otherDetails to set
     */
    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    @Override
    public String toString() {
        return "ItemBrandDTO{" + "braId=" + braId + ", name=" + name + ", description=" + description + ", otherDetails=" + otherDetails + '}';
    }
    
}
