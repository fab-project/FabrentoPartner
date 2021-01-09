package com.app.fabrentopartner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fabrentopartner.Bean.BeanSearchResult;
import com.app.fabrentopartner.Bean.SearchList;
import com.app.fabrentopartner.Utility.ApiConstants;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.adaptor.SearchAdapter;
import com.app.fabrentopartner.fragment.ShareProductFragment;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class SearchActivity extends AppCompatActivity {

    TextView txt_data;
    ImageView btn_back_onSearch;
    RecyclerView rcserach;
    EditText etsearch;
    SearchAdapter searchAdapter;
    private ProgressDialog progress;
    List<SearchList> searchLists = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        progress = new ProgressDialog(SearchActivity.this);
        etsearch = (EditText) findViewById(R.id.etsearch);
        rcserach = (RecyclerView) findViewById(R.id.rcserach);
        txt_data = (TextView) findViewById(R.id.txt_data);
        etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String aftertext = etsearch.getText().toString().trim();
                if (aftertext.length() > 2) {
                    getSearchResult(aftertext);
                }
            }
        });
    }

    private void getSearchResult(String aftertext) {
        searchLists = new ArrayList<>();
        GitApiInterface gitApiInterface = RestClient.getClient();
        final Call<BeanSearchResult> registraionCall = gitApiInterface.getSearchResult("2", aftertext, "Android");
        Log.e("device_id_login", "");
        progress.setMessage("Please Wait ..");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();
        registraionCall.enqueue(new Callback<BeanSearchResult>() {
            @Override
            public void onResponse(Response<BeanSearchResult> response) {
                Log.d("DashBoardResponse", "DashBoard Response = " + response.message());
                progress.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getStatus() == true && response.body() != null) {
                        searchLists = response.body().getSearchList();
                        ApiConstants.Image_Product_Search = response.body().getProductPath() + "/";
                        ApiConstants.Image_Search_Package = response.body().getPackagePath() + "/";
                        ApiConstants.Image_Search_Sticker = response.body().getStickerPath() + "/";
                        if (response.body().getSearchList().size() > 0) {
                            //lvdetail.setVisibility(View.VISIBLE);
                            rcserach.setVisibility(View.VISIBLE);
                            //lvcreditdetail.setVisibility(View.VISIBLE);
                            searchAdapter = new SearchAdapter(SearchActivity.this, searchLists);
                            rcserach.setAdapter(searchAdapter);
                        } else {
                            rcserach.setVisibility(View.GONE);
                        }

                    } else {
                        progress.dismiss();
                        hideKeyboard(SearchActivity.this);
                    }
                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Utility.alertMessage(SearchActivity.this, jObjError.getString("message"));

                    } catch (Exception e) {
                        Toast.makeText(SearchActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                hideKeyboard(SearchActivity.this);

            }
        });
    }

    public void onBack(View view) {
        finish();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

