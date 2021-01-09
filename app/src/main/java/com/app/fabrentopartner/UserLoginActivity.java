package com.app.fabrentopartner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fabrentopartner.Bean.BeanLoginRegis.UserDetail;
import com.app.fabrentopartner.Bean.BeanUserLogin;
import com.app.fabrentopartner.Bean.UserLoginDetail;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.app.fabrentopartner.Utility.PrefUtils;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClient;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static com.app.fabrentopartner.UserRegistration.isValidEmail;

public class UserLoginActivity extends AppCompatActivity {
    @Bind(R.id.etuseremail)
    EditText etuseremail;
    @Bind(R.id.etpassword)
    EditText etpassword;
    @Bind(R.id.btnlogin)
    TextView btnlogin;
    @Bind(R.id.tvsignup)
    TextView tvsignup;
    private ProgressDialog progress;
    String userId;
    String newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        ButterKnife.bind(UserLoginActivity.this);
        tvsignup = (TextView) findViewById(R.id.tvsignup);
        progress = new ProgressDialog(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utility.isNetworkConnected(UserLoginActivity.this)) {
                    if (isValid()) {
                        submitLogin();
                    }
                }
            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utility.isNetworkConnected(UserLoginActivity.this)) {
                    Intent intent = new Intent(UserLoginActivity.this, UserRegistration.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void submitLogin() {
        GitApiInterface gitApiInterface = RestClient.getClient();
        Call<BeanUserLogin> registraionCall = gitApiInterface.postLoginData(etuseremail.getText().toString(), etpassword.getText().toString(), "Content-Type: application/x-www-form-urlencoded");
        progress.setMessage("Please Wait .. ");
        progress.setIndeterminate(true);
        progress.show();
        registraionCall.enqueue(new Callback<BeanUserLogin>() {
            @Override
            public void onResponse(Response<BeanUserLogin> response) {
                Log.d("DashBoardResponse", "DashBoard Response = " + response.message());
                progress.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess() == true) {
                        Log.d("MyResponse", "Response" + response);
                        PrefUtils.getInstance(UserLoginActivity.this).setIsLogin("true");
                        GeneralUtil.Setemailid(UserLoginActivity.this, etuseremail.getText().toString().trim());
                        GeneralUtil.Setpassword(UserLoginActivity.this, etpassword.getText().toString().trim());
                        GeneralUtil.SetUser_id(UserLoginActivity.this, response.body().getUserDetails().get(0).getUserId());
                        PrefUtils.getInstance(UserLoginActivity.this).setIsUser(String.valueOf(response.body().getUserDetails().get(0).getUserId()));
                        GeneralUtil.SetCity(UserLoginActivity.this, response.body().getUserDetails().get(0).getUserCity());
                        GeneralUtil.SetUserAffiliateCode_id(UserLoginActivity.this, response.body().getUserDetails().get(0).getUserAffiliateCode());
                        Log.d("getUserAffiliateCode:", "getUserAffiliateCode Response = " + response.body().getUserDetails().get(0).getUserAffiliateCode());
                        // Utility.alertMessage(UserLoginActivity.this, response.body().getMessage());
                        Intent intent = new Intent(UserLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                       /* PrefUtils.getInstance(UserLoginActivity.this).setIsLogin("true");
                        GeneralUtil.setIsSocialLogin(UserLoginActivity.this, true);
                        GeneralUtil.SetUser_Token(UserLoginActivity.this, response.body().getResponse().getToken());
                        GeneralUtil.SetUser_id(UserLoginActivity.this, String.valueOf(response.body().getResponse().getUserId()));
                        GeneralUtil.SetIsLogin(UserLoginActivity.this, true);
                        GeneralUtil.Setemailid(UserLoginActivity.this, etuseremail.getText().toString().trim());
                        GeneralUtil.SetName(UserLoginActivity.this, response.body().getResponse().getUserName());
                        GeneralUtil.Setdob(UserLoginActivity.this, response.body().getResponse().getDob());
                        GeneralUtil.SetImage(UserLoginActivity.this, response.body().getResponse().getProfileImage());
                        GeneralUtil.Setpassword(UserLoginActivity.this, etpassword.getText().toString().trim());
                        PrefUtils.getInstance(UserLoginActivity.this).setKeycartid(response.body().getResponse().getCartKey());
                        PrefUtils.getInstance(UserLoginActivity.this).setKeyCartCount(response.body().getResponse().getQnt());
                        Log.d("praveen_cont", "my_product_count" + response.body().getResponse().getQnt());
                        //mo
                        if (PrefUtils.getInstance(UserLoginActivity.this).getCityId() == null || PrefUtils.getInstance(UserLoginActivity.this).getCityId().equalsIgnoreCase("")) {
                            Intent intentotp = new Intent(UserLoginActivity.this, SelectCityActivity.class);
                            intentotp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentotp);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                            Toast.makeText(UserLoginActivity.this, response.body().getResponse().getMessages(), Toast.LENGTH_LONG).show();
                        } else {

                            Intent intentotp = new Intent(UserLoginActivity.this, HomeAcrivity.class);
                            intentotp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intentotp);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            Toast.makeText(UserLoginActivity.this, response.body().getResponse().getMessages(), Toast.LENGTH_LONG).show();
                            finish();
                        }*/
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), response.body().getMessage(), Snackbar.LENGTH_LONG);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(Color.parseColor("#e64a19"));
                        TextView tv = (TextView) snackbarView.findViewById(R.id.snackbar_text);
                        tv.setTextColor(Color.WHITE);
                        snackbar.show();
                    } else if (response.body().getSuccess() == false) {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), response.body().getMessage(), Snackbar.LENGTH_LONG);
                        View snackbarView = snackbar.getView();
                        snackbarView.setBackgroundColor(Color.parseColor("#e64a19"));
                        TextView tv = (TextView) snackbarView.findViewById(R.id.snackbar_text);
                        tv.setTextColor(Color.WHITE);
                        snackbar.show();
                        //Utility.alertMessage(UserLoginActivity.this, response.body().getMessage());

                    }
                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        Utility.alertMessage(UserLoginActivity.this, jObjError.getJSONObject("response").getString("message"));

                    } catch (Exception e) {
                        Toast.makeText(UserLoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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

    private boolean isValid() {
        boolean result = true;
        String email = etuseremail.getText().toString();
        String password = etpassword.getText().toString();
        if (email.isEmpty()) {
            etuseremail.setError(Html.fromHtml("<font color=' #E60000'>Please enter email id</font>"));
            etuseremail.requestFocus();
            return false;
        }
        if (!(isValidEmail(email))) {
            etuseremail.setError(Html.fromHtml("<font color=' #E60000'>Please enter valid email id</font>"));
            etuseremail.requestFocus();
            return false;
        }

        if (password.length() > 15 || password.length() < 6) {
            etpassword.setError(Html.fromHtml("<font color=' #E60000'>Password lenght must be greater than 6 or less than 15</font>"));
            etpassword.requestFocus();
            return false;
        }
        return result;
    }
}