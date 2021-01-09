package com.app.fabrentopartner.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeanSearchResult {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("product_path")
    @Expose
    private String productPath;
    @SerializedName("sticker_path")
    @Expose
    private String stickerPath;
    @SerializedName("package_path")
    @Expose
    private String packagePath;
    @SerializedName("search_list")
    @Expose
    private List<SearchList> searchList = null;


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getProductPath() {
        return productPath;
    }

    public void setProductPath(String productPath) {
        this.productPath = productPath;
    }

    public String getStickerPath() {
        return stickerPath;
    }

    public void setStickerPath(String stickerPath) {
        this.stickerPath = stickerPath;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public List<SearchList> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<SearchList> searchList) {
        this.searchList = searchList;
    }


}
