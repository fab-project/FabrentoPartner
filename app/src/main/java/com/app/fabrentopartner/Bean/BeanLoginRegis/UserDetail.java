package com.app.fabrentopartner.Bean.BeanLoginRegis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDetail {

    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("user_associates")
    @Expose
    private List<Object> userAssociates = null;
    @SerializedName("verified")
    @Expose
    private Boolean verified;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_phone")
    @Expose
    private String userPhone;
    @SerializedName("user_fcm")
    @Expose
    private String userFcm;
    @SerializedName("user_referral_id")
    @Expose
    private String userReferralId;
    @SerializedName("user_affiliate_code")
    @Expose
    private String userAffiliateCode;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public List<Object> getUserAssociates() {
        return userAssociates;
    }

    public void setUserAssociates(List<Object> userAssociates) {
        this.userAssociates = userAssociates;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserFcm() {
        return userFcm;
    }

    public void setUserFcm(String userFcm) {
        this.userFcm = userFcm;
    }

    public String getUserReferralId() {
        return userReferralId;
    }

    public void setUserReferralId(String userReferralId) {
        this.userReferralId = userReferralId;
    }

    public String getUserAffiliateCode() {
        return userAffiliateCode;
    }

    public void setUserAffiliateCode(String userAffiliateCode) {
        this.userAffiliateCode = userAffiliateCode;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
