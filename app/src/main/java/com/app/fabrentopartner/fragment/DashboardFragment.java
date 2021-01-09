package com.app.fabrentopartner.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.app.fabrentopartner.Bean.BeanBarChart.BarChartResponce;
import com.app.fabrentopartner.Bean.BeanBarChart.BarChartStrikeRate;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.Utility.PrefUtils;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClientNew;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.snackbar.Snackbar;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView re_top_list;
    ProgressDialog progress;
    BarChart barChart;
    PieChart mPieChart;
    ImageView btn_back;
    TextView text_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        progress = new ProgressDialog(getActivity());
        text_title = (TextView) view.findViewById(R.id.text_title);
        btn_back = (ImageView) view.findViewById(R.id.btn_back);
        barChart = view.findViewById(R.id.barChart);
        //pieChart=view.findViewById(R.id.piechart);
        mPieChart = view.findViewById(R.id.piechart);
        btn_back.setVisibility(View.GONE);
        text_title.setText("Fabrento Partner");

        methodbarChart();

/*
        StackedBarChart mStackedBarChart = (StackedBarChart) view.findViewById(R.id.stackedbarchart);

        StackedBarModel s1 = new StackedBarModel("12.4");

        s1.addBar(new BarModel(2.3f, 0xFF63CBB0));
        s1.addBar(new BarModel(2.3f, 0xFF56B7F1));
        s1.addBar(new BarModel(2.3f, 0xFFCDA67F));

        StackedBarModel s2 = new StackedBarModel("13.4");
        s2.addBar(new BarModel(1.1f, 0xFF63CBB0));
        s2.addBar(new BarModel(2.7f, 0xFF56B7F1));
        s2.addBar(new BarModel(0.7f, 0xFFCDA67F));

        StackedBarModel s3 = new StackedBarModel("14.4");

        s3.addBar(new BarModel(2.3f, 0xFF63CBB0));
        s3.addBar(new BarModel(2.f, 0xFF56B7F1));
        s3.addBar(new BarModel(3.3f, 0xFFCDA67F));

        StackedBarModel s4 = new StackedBarModel("15.4");
        s4.addBar(new BarModel(1.f, 0xFF63CBB0));
        s4.addBar(new BarModel(4.2f, 0xFF56B7F1));
        s4.addBar(new BarModel(2.1f, 0xFFCDA67F));
*/

/*
        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);
        mStackedBarChart.addBar(s4);

        mStackedBarChart.startAnimation();
*/


        /**********************Set Static data For Pie chart******************/
      /*  mPieChart.addPieSlice(new PieModel("Freetime", 15, Color.parseColor("#FE6DA8")));
        mPieChart.addPieSlice(new PieModel("Sleep", 25, Color.parseColor("#56B7F1")));
        mPieChart.addPieSlice(new PieModel("Work", 35, Color.parseColor("#CDA67F")));
        mPieChart.addPieSlice(new PieModel("Eating", 9, Color.parseColor("#FED70E")));
        mPieChart.startAnimation();*/


        /**********************Set Static data For bar chart******************/
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 500));
        barEntries.add(new BarEntry(2, 400));
        barEntries.add(new BarEntry(3, 300));
        barEntries.add(new BarEntry(4, 300));
        barEntries.add(new BarEntry(5, 600));
        barEntries.add(new BarEntry(6, 600));
        barEntries.add(new BarEntry(7, 100));

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Sun");
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");

        BarDataSet barDataSet = new BarDataSet(barEntries, "Product");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(1f);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String label = "label";
                try {
                    label = xAxisLabel.get((int) value);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                return label;
            }
        };
        xAxis.setValueFormatter(formatter);
        BarData barData = new BarData(barDataSet);

        barChart.setTouchEnabled(true);
        barChart.setClickable(false);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.setDoubleTapToZoomEnabled(false);

        barChart.getXAxis().setDrawGridLines(false);
        barChart.getXAxis().setDrawLabels(true);
        barChart.getXAxis().setDrawAxisLine(false);

        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.setFitBars(true);
        /*barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(2000);*/

        return view;
    }


    private void methodbarChart() {
        GitApiInterface gitApiInterface = RestClientNew.getClient(getActivity());
        final Call<BarChartResponce> registraionCall = gitApiInterface.postbarchart(PrefUtils.getInstance(getContext()).getKeyUser(), "2020-12-20T00:00:00.000Z", "2021-01-07T00:00:00.000Z", "Content-Type: application/x-www-form-urlencoded");
        progress.setMessage("Please Wait ..");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();
        final ArrayList<String> colorList = new ArrayList<>();
        colorList.add("#FE6DA8");
        colorList.add("#56B7F1");
        colorList.add("#CDA67F");
        colorList.add("#CDA67F");
        colorList.add("#FED70E");
        registraionCall.enqueue(new Callback<BarChartResponce>() {
            @Override
            public void onResponse(Response<BarChartResponce> response) {
                Log.d("DashBoardResponsebar", "DashBoard Response = " + response.message());
                progress.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess() == true && response.body() != null) {
                        BarChartResponce barChartResponce = response.body();
                        ArrayList<BarEntry> barEntries = new ArrayList<>();
                        final ArrayList<String> xAxisLabel = new ArrayList<>();
                        for (int i = 0; i < barChartResponce.getDetails().getRevenue().size(); i++) {
                            BarChartStrikeRate barChartStrikeRate = (BarChartStrikeRate) barChartResponce.getDetails().getRevenue().get(i);
                            mPieChart.addPieSlice(new PieModel(barChartStrikeRate.getId(), barChartStrikeRate.getCount(), Color.parseColor(colorList.get(i))));
                            barEntries.add(new BarEntry(i, barChartStrikeRate.getCount()));
                            xAxisLabel.add(barChartStrikeRate.getId());
                        }
                        mPieChart.startAnimation();

                        BarDataSet barDataSet = new BarDataSet(barEntries, "Product");
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(16f);

                        XAxis xAxis = barChart.getXAxis();
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setLabelCount(1, false);
                        ValueFormatter formatter = new ValueFormatter() {
                            @Override
                            public String getFormattedValue(float value) {
                                String label = "label";
                                try {
                                    label = xAxisLabel.get((int) value);
                                } catch (IndexOutOfBoundsException e) {
                                    e.printStackTrace();
                                }
                                return label;
                            }
                        };
                        xAxis.setValueFormatter(formatter);
                        BarData barData = new BarData(barDataSet);
                        barChart.setData(barData);
                        barChart.getDescription().setText("Revenue Data");
                        barChart.animateY(2000);
                    }
                } else if (response.body().getSuccess() == false) {
                    Utility.alertMessage(getActivity(), response.body().getMessage());
                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), response.body().getMessage(), Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(Color.parseColor("#29B6F6"));
                    TextView tv = (TextView) snackbarView.findViewById(R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
