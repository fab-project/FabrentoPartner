package com.app.fabrentopartner.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeanUserLogin {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_details")
    @Expose
    private List<UserLoginDetail> userDetails = null;
    @SerializedName("user_token")
    @Expose
    private String userToken;

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

    public List<UserLoginDetail> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserLoginDetail> userDetails) {
        this.userDetails = userDetails;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }


}
