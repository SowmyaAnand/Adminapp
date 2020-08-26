//Data package for  main category
package com.dailyestoreapp.adminapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListCategoryResponseData {

    @SerializedName("typeId")
    @Expose
    private String typeId;
    @SerializedName("itemName")
    @Expose
    private String itemName;
    @SerializedName("itemImage")
    @Expose
    private String itemImage;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("subId")
    @Expose
    private String subId;

    @SerializedName("subName")
    @Expose
    private String subName;
    @SerializedName("subItemImage")
    @Expose
    private String subItemImage;
    @SerializedName("itemId")
    @Expose
    private String itemId;

    @SerializedName("itemTypeName")
    @Expose
    private String itemTypeName;

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("image")
    @Expose
    private String image;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedBy() {
        return createdBy;
    }



    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }



    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
