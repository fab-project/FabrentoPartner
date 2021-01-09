package com.app.fabrentopartner.BeanFetchLeadData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchLeadResponce {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private List<BeanFetchDetail> details = null;

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

    public List<BeanFetchDetail> getDetails() {
        return details;
    }

    public void setDetails(List<BeanFetchDetail> details) {
        this.details = details;
    }
}
