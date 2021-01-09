package com.app.fabrentopartner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.fabrentopartner.Bean.BeanLoginRegis.RegistrationResponse;
import com.app.fabrentopartner.Bean.UpdateResponse;
import com.app.fabrentopartner.Utility.ApiConstants;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClient;
import com.app.fabrentopartner.retrofit.RestClientNew;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MyProfile extends AppCompatActivity {

    TextView textView, nameTv, mobileTv, emailTv;
    EditText nameEt, emailEt, PhoneEt;
    String name, email, phone;
    LinearLayout showLayout, editL, cancelLayout;
    LinearLayout editLayout;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        progressDialog = new ProgressDialog(this);
        textView = (TextView) findViewById(R.id.text_title);
        editL = (LinearLayout) findViewById(R.id.editL);
        nameEt = (EditText) findViewById(R.id.nameEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        PhoneEt = (EditText) findViewById(R.id.PhoneEt);
        showLayout = (LinearLayout) findViewById(R.id.showLayout);
        editLayout = (LinearLayout) findViewById(R.id.editLayout);
        cancelLayout = (LinearLayout) findViewById(R.id.cancelLayout);
        editL.setVisibility(View.VISIBLE);
        textView.setText("My Profile");
        nameTv = (TextView) findViewById(R.id.nameTv);
        mobileTv = (TextView) findViewById(R.id.mobileTv);
        emailTv = (TextView) findViewById(R.id.emailTv);
        nameTv.setText(GeneralUtil.GetName(MyProfile.this));
        mobileTv.setText(GeneralUtil.getMobileno(MyProfile.this));
        emailTv.setText(GeneralUtil.Getemailid(MyProfile.this));

        showLayout.setVisibility(View.VISIBLE);
        editLayout.setVisibility(View.GONE);
        nameEt.setText(GeneralUtil.GetName(MyProfile.this));
        emailEt.setText(GeneralUtil.Getemailid(MyProfile.this));
        PhoneEt.setText(GeneralUtil.getMobileno(MyProfile.this));
        editL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLayout.setVisibility(View.GONE);
                editLayout.setVisibility(View.VISIBLE);
                cancelLayout.setVisibility(View.VISIBLE);
                editL.setVisibility(View.GONE);
            }
        });
        cancelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLayout.setVisibility(View.VISIBLE);
                editLayout.setVisibility(View.GONE);
                cancelLayout.setVisibility(View.GONE);
                editL.setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.proceedButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEt.getText().toString().trim();
                email = emailEt.getText().toString().trim();
                phone = PhoneEt.getText().toString().trim();

                if (name.equals("")) {
                    Toast.makeText(MyProfile.this, "Enter full name", Toast.LENGTH_SHORT).show();

                } else if (email.equals("")) {
                    Toast.makeText(MyProfile.this, "Enter full email address", Toast.LENGTH_SHORT).show();
                } else if (!Utility.isValidEmailId(email)) {
                    Toast.makeText(MyProfile.this, "Please enter valid email address", Toast.LENGTH_SHORT).show();
                } else if (phone.equals("")) {
                    Toast.makeText(MyProfile.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utility.isNetworkConnected(MyProfile.this)) {
                        updateMethod(name, email, phone);
                    } else {
                        Utility.alertMessage(MyProfile.this, getString(R.string.noiternet));
                    }
                }
            }
        });

    }

    // image upload
    private void updateMethod(final String name, final String email, final String phone) {
        GitApiInterface gitApiInterface = RestClientNew.getClient(MyProfile.this);
        Call<UpdateResponse> registraionCall = gitApiInterface.postUpdatedata(name, GeneralUtil.Get_UserId(MyProfile.this), GeneralUtil.GetCity(MyProfile.this), "Content-Type: application/x-www-form-urlencoded");
        progressDialog.setMessage("Please Wait .. ");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        registraionCall.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Response<UpdateResponse> response) {
                Log.d("DashBoardResponse", "DashBoard Response = " + response.message());
                progressDialog.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess()) {
                        Log.d("MySuccess", "getSuccess" + response.message());
                        Utility.alertMessage(MyProfile.this, response.body().getMessage());
                        editLayout.setVisibility(View.GONE);
                        showLayout.setVisibility(View.VISIBLE);
                        nameTv.setText(GeneralUtil.GetName(MyProfile.this));
                        mobileTv.setText(GeneralUtil.getMobileno(MyProfile.this));
                        emailTv.setText(GeneralUtil.Getemailid(MyProfile.this));
                    } else if (response.body().getSuccess() == false) {

                        Utility.alertMessage(MyProfile.this, response.body().getMessage());
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Utility.alertMessage(MyProfile.this, jObjError.getJSONObject("response").getString("message"));
                    } catch (Exception e) {
                        Toast.makeText(MyProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }


            @Override
            public void onFailure(Throwable t) {
                Log.d("register", "Hello" + t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(MyProfile.this, UserRegistration.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}