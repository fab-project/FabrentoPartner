package com.app.fabrentopartner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;

import com.app.fabrentopartner.Bean.BeanUserLogin;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.app.fabrentopartner.Utility.PrefUtils;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class WelcomeActivity extends AppCompatActivity {
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_welcome);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(WelcomeActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String mToken = instanceIdResult.getToken();
                Log.e("Fcm-Token: ", mToken);
                PrefUtils.getInstance(WelcomeActivity.this).setFcmToken(mToken);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.equals(PrefUtils.getInstance(WelcomeActivity.this).getLogin(), "true")) {
                    login(GeneralUtil.Getemailid(WelcomeActivity.this), GeneralUtil.Getpassword(WelcomeActivity.this));
                } else {
                    Intent i = new Intent(WelcomeActivity.this, UserLoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                }
            }
        }, 1000);
    }

    private void login(final String getemailid, final String getpassword) {
        GitApiInterface gitApiInterface = RestClient.getClient();
        Call<BeanUserLogin> registraionCall = gitApiInterface.postLoginData(getemailid, getpassword, "Content-Type: application/x-www-form-urlencoded");
        progress.setMessage("Please Wait .. ");
        progress.setIndeterminate(true);
        progress.show();
        registraionCall.enqueue(new Callback<BeanUserLogin>() {
            @Override
            public void onResponse(Response<BeanUserLogin> response) {
                Log.d("DashBoardResponse", "Response = " + response.message());
                progress.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess() == true) {
                        Log.d("DashBoardResponse", "Response1 = " + response.message());
                        PrefUtils.getInstance(WelcomeActivity.this).setIsLogin("true");
                        GeneralUtil.SetUser_Token(WelcomeActivity.this, response.body().getUserToken());
                        GeneralUtil.SetUser_id(WelcomeActivity.this, response.body().getUserDetails().get(0).getUserId());
                        GeneralUtil.SetCity(WelcomeActivity.this, response.body().getUserDetails().get(0).getUserCity());
                        GeneralUtil.SetUserAffiliateCode_id(WelcomeActivity.this, response.body().getUserDetails().get(0).getUserAffiliateCode());
                        GeneralUtil.SetIsLogin(WelcomeActivity.this, true);
                        GeneralUtil.Setemailid(WelcomeActivity.this, getemailid);
                        GeneralUtil.Setpassword(WelcomeActivity.this, getpassword);
                        Intent intentotp = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intentotp);
                        finish();
                    } else if (response.body().getSuccess() == false) {
                        GeneralUtil.SetIsLogin(WelcomeActivity.this, false);
                        // PrefUtils.getInstance(WelcomeActivity.this).setIsLogin("false");
                        PrefUtils.getInstance(WelcomeActivity.this).setIsLogin("");
                        GeneralUtil.SetName(WelcomeActivity.this, "");

                        Intent intentotp = new Intent(WelcomeActivity.this, UserLoginActivity.class);
                        intentotp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intentotp);
                    }
                } else {

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        // Utility.alertMessage(WelcomeActivity.this, jObjError.getJSONObject("response").getString("messages"));

                    } catch (Exception e) {
                        //Toast.makeText(WelcomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
