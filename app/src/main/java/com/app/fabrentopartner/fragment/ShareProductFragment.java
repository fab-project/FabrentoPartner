package com.app.fabrentopartner.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.app.fabrentopartner.Bean.BeaProductCat;
import com.app.fabrentopartner.Bean.BeanAllProduct;
import com.app.fabrentopartner.Bean.BeanProduct;
import com.app.fabrentopartner.Bean.Datum;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.SearchActivity;
import com.app.fabrentopartner.Utility.ApiConstants;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.adaptor.ProductRecycleAdapter;
import com.app.fabrentopartner.adaptor.RecycleviewTopCatAdapter;
import com.app.fabrentopartner.adaptor.SubCatAdapter;
import com.app.fabrentopartner.retrofit.BtnClickListener;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClientNewPro;
import com.app.fabrentopartner.retrofit.RestClientProduct;
import com.app.fabrentopartner.retrofit.SubClickListener;
import com.google.gson.Gson;

import org.json.JSONObject;


import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShareProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShareProductFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShareProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShareProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShareProductFragment newInstance(String param1, String param2) {
        ShareProductFragment fragment = new ShareProductFragment();
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

    String category_id;
    BtnClickListener btnClickListener;
    RecyclerView re_top_list;
    ProgressDialog progress;
    int position = 0;

    //second list
    RecyclerView re_sub_list;
    ProgressDialog progress_sub;
    SubClickListener subClickListener;
    String subcatid;


    //Three list
    TextView text_title;
    ImageView btn_search;
    RecyclerView re_three_list;
    ProgressDialog progress_last;
    private GridLayoutManager lLayout;
    GridLayoutManager gridLayoutManager;
    ProductRecycleAdapter productRecycleAdapter;
    List<Datum> result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share_product, container, false);
        re_top_list = view.findViewById(R.id.re_top_list);
        re_sub_list = view.findViewById(R.id.re_sub_list);
        re_three_list = view.findViewById(R.id.re_three_list);
        text_title = view.findViewById(R.id.text_title);
        btn_search = (ImageView) view.findViewById(R.id.btn_search);
        btn_search.setVisibility(View.VISIBLE);
        text_title.setText("Share Product");
        lLayout = new GridLayoutManager(getContext(), 2);
        re_top_list.setNestedScrollingEnabled(false);
        re_sub_list.setNestedScrollingEnabled(false);
        re_three_list.setNestedScrollingEnabled(false);

        getRandomDataProductList();


        btnClickListener = new BtnClickListener() {
            @Override
            public void onBtnClick(String id) {
                category_id = id;
                getProductSubCatnew("2", category_id);
            }

        };
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                startActivity(intent);

            }
        });
        subClickListener = new SubClickListener() {
            @Override
            public void onSubClick(String id) {
                subcatid = id;
               /* PAGE_START = 1;
                CURRENT_PAGE = PAGE_START;
                isLoading = false;
                isLastPage = false;*/
                System.out.println("user_sub_cat_id" + subcatid);
//                productRecycleAdapter.listclean();
                quicklinklist(subcatid, subcatid, "", "", "", "");
                productRecycleAdapter.notifyDataSetChanged();
            }
        };
        return view;
    }


    private void getRandomDataProductList() {
        GitApiInterface gitApiInterface = RestClientProduct.getClient();
        Call<BeanAllProduct> registraionCall = gitApiInterface.getAllProduct("2", "main_cat");
        progress = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress.setMessage("Please Wait...");
        progress.show();
        registraionCall.enqueue(new Callback<BeanAllProduct>() {
            @Override
            public void onResponse(final Response<BeanAllProduct> response) {
                Log.e("TAG", "first_list" + new Gson().toJson(response.body()));
                progress.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getStatus() == true) {
                        re_top_list.setVisibility(View.VISIBLE);
                        if (response.body().getCatList().size() > 0) {
                            ApiConstants.Image_Base_product_Url = response.body().getCategoryPath() + "/";
                            re_top_list.setAdapter(new RecycleviewTopCatAdapter(getActivity(), response.body().getCatList(), position, btnClickListener));
                            re_top_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            re_top_list.setNestedScrollingEnabled(false);
                            category_id = response.body().getCatList().get(position).getSlug().toString();
                            if (position == 0) {
                                getProductSubCatnew("2", category_id);
                            }

                        } else {
                            Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_LONG).show();

                        }

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("first_list_error", "" + t.getMessage());
                progress.dismiss();
            }
        });
    }

    private void getProductSubCatnew(String cityId, final String category_id) {
        GitApiInterface gitApiInterface = RestClientProduct.getClient();
        Call<BeaProductCat> registraionCall = gitApiInterface.productcatlist("2", category_id);
        progress_sub = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress_sub.setMessage("Please Wait...");
        progress_sub.show();
        registraionCall.enqueue(new Callback<BeaProductCat>() {
            @Override
            public void onResponse(Response<BeaProductCat> response) {
                Log.e("TAG", "second_list" + new Gson().toJson(response.body()));
                progress_sub.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getStatus() == true) {
                        if (response.body().getCategoryList().size() > 0) {
                            re_sub_list.setAdapter(new SubCatAdapter(getActivity(), response.body().getCategoryList(), position, subClickListener));
                            re_sub_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                            subcatid = String.valueOf(response.body().getCategoryList().get(0).getId());
                            re_sub_list.setNestedScrollingEnabled(false);
                            System.out.println("subcatid_ss" + subcatid);
                            if (position == 0) {
                                quicklinklist(subcatid, subcatid, null, null, null, null);
                                /*sw_refershview.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        PAGE_START = 1;
                                        CURRENT_PAGE = PAGE_START;
                                        isLoading = false;
                                        isLastPage = false;
                                        System.out.println("user_sub_cat_id_new" + subcatid);
                                        quicklinklist(subcatid, subcatid, MiniDesposit, MaxDesposit, monthly_rental, sortby);
                                    }
                                });*/
                            }


                        } else {
                            Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_LONG).show();

                        }

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("second_list_error", "" + t.getMessage());
                progress_sub.dismiss();
            }
        });

    }

    private void quicklinklist(String category_id, String subcatid, String MiniDesposit, String MaxDesposit, String monthly_rental, String sortby) {
        //sw_refershview.setRefreshing(true);
        GitApiInterface gitApiInterface = RestClientNewPro.getClient(getContext());
        Call<BeanProduct> registraionCall = gitApiInterface.productList("2", category_id, subcatid, "", MiniDesposit, MaxDesposit, monthly_rental, sortby, "", "1", "Android");
        progress_last = new ProgressDialog(getContext());
        progress.setCancelable(false);
        progress_last.setMessage("Please Wait .. ");
        progress_last.setIndeterminate(true);
        progress_last.show();
        registraionCall.enqueue(new Callback<BeanProduct>() {
            @Override
            public void onResponse(final Response<BeanProduct> response) {
                Log.e("TAG", "thired_list" + new Gson().toJson(response.body()));
                progress_last.dismiss();
                if (response.isSuccess()) {
                    //sw_refershview.setRefreshing(false);
                    if (response.body().getStatus() == true) {
                        ApiConstants.Image_Base_Product_List_Url = response.body().getPath() + "/";
                        re_three_list.setHasFixedSize(true);
                        re_three_list.setLayoutManager(lLayout);
                        if (response.body().getProducts().getData().size() > 0) {
                            productRecycleAdapter = new ProductRecycleAdapter(response.body().getProducts().getData(), getActivity());
                            re_three_list.setAdapter(productRecycleAdapter);
                            re_three_list.setNestedScrollingEnabled(false);
                           /* if (CURRENT_PAGE <= TOTAL_PAGE) {
                            } else {
                                isLastPage = true;
                            }*/
                            //progressBar.setVisibility(View.GONE);
                            progress_last.dismiss();
                            /*filter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    filter.setColorFilter(getContext().getResources().getColor(R.color.color_red));
                                    Intent intent = new Intent(getContext(), FilterScreenActivity.class);
                                    intent.putExtra("filterdata", (Serializable) response.body().getFilterSection());
                                    startActivityForResult(intent, 5);
                                    getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

                                }
                            });*/
                        }


                    }
                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        Utility.alertMessage(getContext(), jObjError.getJSONObject("response").getString("message"));

                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("register", "Hello" + t.getMessage());
                progress_last.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
