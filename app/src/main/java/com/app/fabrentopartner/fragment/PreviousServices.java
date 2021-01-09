package com.app.fabrentopartner.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.BeanFetchLeadData.BeanFetchDetail;
import com.app.fabrentopartner.BeanFetchLeadData.FetchLeadResponce;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.app.fabrentopartner.Utility.PrefUtils;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.adaptor.PreviousServicesAdapter;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClientNew;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class PreviousServices extends Fragment {
    private List<BeanFetchDetail> beanFetchDetail;
    PreviousServicesAdapter servicesAdapter;

    public static ImageView img_filter;

    private ProgressDialog progress;
    RecyclerView recyclerView;
    Context context;
    View view;

    public PreviousServices() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_services_layout, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        beanFetchDetail = new ArrayList<>();
        progress = new ProgressDialog(getActivity());
        recyclerView = view.findViewById(R.id.recyclerView);

        img_filter = view.findViewById(R.id.img_filter);
        img_filter.setVisibility(View.VISIBLE);
        img_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.img_filter:
                        showFirstBottomDialogg();
                        break;
                }

            }
        });
        if (Utility.isNetworkConnected(getActivity())) {
            getFetchData("All");
        }


    }

    private void showFirstBottomDialogg() {
        final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.filterbottom, null);
        final TextView txt_pending_dialog = contentView.findViewById(R.id.txt_pending_dialog);
        final TextView txt_cold_dialog = contentView.findViewById(R.id.txt_cold_dialog);
        final ImageView img_dot = contentView.findViewById(R.id.img_dot);
        final TextView txt_allocated_dialog = contentView.findViewById(R.id.txt_allocated_dialog);
        final TextView txt_hot_dialog = contentView.findViewById(R.id.txt_hot_dialog);
        final TextView txt_convert_dialog = contentView.findViewById(R.id.txt_convert_dialog);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        txt_pending_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), txt_cold_dialog.getText().toString(), Toast.LENGTH_SHORT).show();
                //bottomDialog.hide();
                getFetchData(txt_pending_dialog.getText().toString());
                //img_dot.setVisibility(View.VISIBLE);
            }
        });
        txt_cold_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), txt_cold_dialog.getText().toString(), Toast.LENGTH_SHORT).show();
                bottomDialog.hide();
                getFetchData(txt_cold_dialog.getText().toString());
            }
        });
        txt_allocated_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), txt_cold_dialog.getText().toString(), Toast.LENGTH_SHORT).show();
                bottomDialog.hide();
                getFetchData(txt_allocated_dialog.getText().toString());
            }
        });
        txt_hot_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), txt_cold_dialog.getText().toString(), Toast.LENGTH_SHORT).show();
                bottomDialog.hide();
                getFetchData(txt_hot_dialog.getText().toString());
            }
        });
        txt_convert_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), txt_cold_dialog.getText().toString(), Toast.LENGTH_SHORT).show();
                bottomDialog.hide();
                getFetchData(txt_convert_dialog.getText().toString());
            }
        });
    }
    private void getFetchData(String All) {
        GitApiInterface gitApiInterface = RestClientNew.getClient(getActivity());
        Call<FetchLeadResponce> registraionCall = gitApiInterface.postfetchLeaddata(All, PrefUtils.getInstance(context).getKeyUser(), "Content-Type: application/x-www-form-urlencoded");
        progress.setMessage("Please Wait ..");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();
        registraionCall.enqueue(new Callback<FetchLeadResponce>() {
            @Override
            public void onResponse(Response<FetchLeadResponce> response) {
                Log.d("DashBoardResponse", "DashBoard Response = " + response.message());
                progress.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess() == true && response.body() != null) {
                        beanFetchDetail = response.body().getDetails();
                        if (beanFetchDetail.size() > 0) {
                            // Log.d("scucess", "scucessResponse = " + response.message());
                            // Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            servicesAdapter = new PreviousServicesAdapter(getActivity(), beanFetchDetail);
                            recyclerView.setAdapter(servicesAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                        }
                    } else if (response.body().getSuccess() == false) {
                        Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content),  response.body().getMessage(), Snackbar.LENGTH_LONG);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(Color.parseColor("#29B6F6"));
                        TextView tv = (TextView) snackbarView.findViewById(R.id.snackbar_text);
                        tv.setTextColor(Color.WHITE);
                        snackbar.show();
                       // Utility.alertMessage(getActivity(), response.body().getMessage());
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Utility.alertMessage(context, jObjError.getJSONObject("response").getString("message"));
                    } catch (Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("register", "Hello" + t.getMessage());
                progress.dismiss();
            }
        });
    }
}

