package com.app.fabrentopartner.Bean.BeanBarChart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BarChartDetails {

    @SerializedName("strike_rate")
    @Expose
    private List<BarChartStrikeRate> strikeRate = null;
    @SerializedName("revenue")
    @Expose
    private List<Object> revenue = null;

    public List<BarChartStrikeRate> getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(List<BarChartStrikeRate> strikeRate) {
        this.strikeRate = strikeRate;
    }

    public List<Object> getRevenue() {
        return revenue;
    }

    public void setRevenue(List<Object> revenue) {
        this.revenue = revenue;
    }
}
