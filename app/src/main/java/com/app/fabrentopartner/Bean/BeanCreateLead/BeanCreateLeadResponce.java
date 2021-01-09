package com.app.fabrentopartner.Bean.BeanCreateLead;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeanCreateLeadResponce {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private CreateLeadDetails details;

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

    public CreateLeadDetails getDetails() {
        return details;
    }

    public void setDetails(CreateLeadDetails details) {
        this.details = details;
    }
}
