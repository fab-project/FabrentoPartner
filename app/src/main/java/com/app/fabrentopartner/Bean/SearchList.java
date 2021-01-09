package com.app.fabrentopartner.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchList {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("monthly_rental")
    @Expose
    private String monthlyRental;
    @SerializedName("deposit")
    @Expose
    private String deposit;
    @SerializedName("stock_status")
    @Expose
    private String stockStatus;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("stitle")
    @Expose
    private Object stitle;
    @SerializedName("simage")
    @Expose
    private Object simage;
    @SerializedName("monthly_rental_price")
    @Expose
    private Object monthlyRentalPrice;
    @SerializedName("product_type")
    @Expose
    private String productType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getMonthlyRental() {
        return monthlyRental;
    }

    public void setMonthlyRental(String monthlyRental) {
        this.monthlyRental = monthlyRental;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getStitle() {
        return stitle;
    }

    public void setStitle(Object stitle) {
        this.stitle = stitle;
    }

    public Object getSimage() {
        return simage;
    }

    public void setSimage(Object simage) {
        this.simage = simage;
    }

    public Object getMonthlyRentalPrice() {
        return monthlyRentalPrice;
    }

    public void setMonthlyRentalPrice(Object monthlyRentalPrice) {
        this.monthlyRentalPrice = monthlyRentalPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
