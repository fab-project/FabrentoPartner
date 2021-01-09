package com.app.fabrentopartner.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeanAllProduct {
    @SerializedName("catList")
    @Expose
    private List<CatList> catList = null;
    @SerializedName("category_path")
    @Expose
    private String categoryPath;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("msg")
    @Expose
    private String msg;
    /*@SerializedName("filter_section")
    @Expose
    private List<FilterSection> filterSection = null;*/

    public List<CatList> getCatList() {
        return catList;
    }

    public void setCatList(List<CatList> catList) {
        this.catList = catList;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
   /* public List<FilterSection> getFilterSection() {
        return filterSection;
    }

    public void setFilterSection(List<FilterSection> filterSection) {
        this.filterSection = filterSection;
    }*/
}
