package com.app.fabrentopartner.Bean.BeanBarChart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BarChartResponce {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private BarChartDetails details;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BarChartDetails getDetails() {
        return details;
    }

    public void setDetails(BarChartDetails details) {
        this.details = details;
    }

}