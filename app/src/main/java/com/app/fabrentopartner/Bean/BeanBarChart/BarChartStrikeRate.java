package com.app.fabrentopartner.Bean.BeanBarChart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BarChartStrikeRate {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("count")
    @Expose
    private Integer count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}