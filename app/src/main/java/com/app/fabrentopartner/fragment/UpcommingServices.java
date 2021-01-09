package com.app.fabrentopartner.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.BeanFetchLeadData.BeanFetchDetail;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.adaptor.UpcomingServicesAdapter;

import java.util.List;

import static com.app.fabrentopartner.fragment.PreviousServices.img_filter;


public class UpcommingServices extends Fragment {
    private UpcomingServicesAdapter servicesAdapter;
    private List<BeanFetchDetail> beanFetchDetail;
    RecyclerView recyclerView;
    Context context;
    View view;
    private ProgressDialog progress;

    public UpcommingServices() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_services_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        img_filter = view.findViewById(R.id.img_filter);
        img_filter.setVisibility(View.GONE);
        servicesAdapter = new UpcomingServicesAdapter(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(servicesAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

}
