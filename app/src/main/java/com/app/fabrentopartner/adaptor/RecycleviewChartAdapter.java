package com.app.fabrentopartner.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.R;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

public class RecycleviewChartAdapter extends RecyclerView.Adapter<RecycleviewChartAdapter.HolderChartManager> {

    private Context context;
    ArrayList barEntries;
    public RecycleviewChartAdapter(ArrayList barEntries) {
        this.barEntries = barEntries;

    }

    @NonNull
    @Override
    public RecycleviewChartAdapter.HolderChartManager onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productchartlist, parent, false);
        RecycleviewChartAdapter.HolderChartManager holderEstateManager = new RecycleviewChartAdapter.HolderChartManager(view);
        return holderEstateManager;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderChartManager holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HolderChartManager extends RecyclerView.ViewHolder {
        BarChart barChart;

        public HolderChartManager(@NonNull View itemView) {
            super(itemView);
            barChart = itemView.findViewById(R.id.barChart);
        }
    }
}
