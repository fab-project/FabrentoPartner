package com.app.fabrentopartner.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BeanProduct implements Serializable {
    @SerializedName("products")
    @Expose
    private Products products;
    /* @SerializedName("filter_section")
     @Expose
     private List<FilterSection> filterSection = null;*/
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("sticker_path")
    @Expose
    private String stickerPath;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

   /* public List<FilterSection> getFilterSection() {
        return filterSection;
    }

    public void setFilterSection(List<FilterSection> filterSection) {
        this.filterSection = filterSection;
    }*/

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStickerPath() {
        return stickerPath;
    }

    public void setStickerPath(String stickerPath) {
        this.stickerPath = stickerPath;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
