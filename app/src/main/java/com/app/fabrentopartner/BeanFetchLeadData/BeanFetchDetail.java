package com.app.fabrentopartner.BeanFetchLeadData;

import com.app.fabrentopartner.Bean.BeanCreateLead.Random;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeanFetchDetail {
    @SerializedName("random")
    @Expose
    private Random random;
    @SerializedName("lead_type")
    @Expose
    private String leadType;
    @SerializedName("lead_order_id")
    @Expose
    private String leadOrderId;
    @SerializedName("allocated_to")
    @Expose
    private List<Object> allocatedTo = null;
    @SerializedName("lead_status")
    @Expose
    private String leadStatus;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("lead_id")
    @Expose
    private String leadId;
    @SerializedName("lead_name")
    @Expose
    private String leadName;
    @SerializedName("lead_contact")
    @Expose
    private String leadContact;
    @SerializedName("lead_email")
    @Expose
    private String leadEmail;
    @SerializedName("lead_address")
    @Expose
    private String leadAddress;
    @SerializedName("lead_city")
    @Expose
    private String leadCity;
    @SerializedName("lead_description")
    @Expose
    private String leadDescription;
    @SerializedName("lead_comment")
    @Expose
    private String leadComment;
    @SerializedName("lead_total_amount")
    @Expose
    private Integer leadTotalAmount;
    @SerializedName("lead_share_amount")
    @Expose
    private Integer leadShareAmount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getLeadType() {
        return leadType;
    }

    public void setLeadType(String leadType) {
        this.leadType = leadType;
    }

    public String getLeadOrderId() {
        return leadOrderId;
    }

    public void setLeadOrderId(String leadOrderId) {
        this.leadOrderId = leadOrderId;
    }

    public List<Object> getAllocatedTo() {
        return allocatedTo;
    }

    public void setAllocatedTo(List<Object> allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadContact() {
        return leadContact;
    }

    public void setLeadContact(String leadContact) {
        this.leadContact = leadContact;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getLeadAddress() {
        return leadAddress;
    }

    public void setLeadAddress(String leadAddress) {
        this.leadAddress = leadAddress;
    }

    public String getLeadCity() {
        return leadCity;
    }

    public void setLeadCity(String leadCity) {
        this.leadCity = leadCity;
    }

    public String getLeadDescription() {
        return leadDescription;
    }

    public void setLeadDescription(String leadDescription) {
        this.leadDescription = leadDescription;
    }

    public String getLeadComment() {
        return leadComment;
    }

    public void setLeadComment(String leadComment) {
        this.leadComment = leadComment;
    }

    public Integer getLeadTotalAmount() {
        return leadTotalAmount;
    }

    public void setLeadTotalAmount(Integer leadTotalAmount) {
        this.leadTotalAmount = leadTotalAmount;
    }

    public Integer getLeadShareAmount() {
        return leadShareAmount;
    }

    public void setLeadShareAmount(Integer leadShareAmount) {
        this.leadShareAmount = leadShareAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }
}
