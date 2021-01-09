package com.app.fabrentopartner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fabrentopartner.Bean.BeanLoginRegis.RegistrationResponse;
import com.app.fabrentopartner.Bean.BeanLoginRegis.UserDetail;
import com.app.fabrentopartner.Utility.ApiConstants;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.app.fabrentopartner.Utility.PrefUtils;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClient;
import com.google.android.material.textfield.TextInputEditText;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class UserRegistration extends AppCompatActivity {
    private List<UserDetail> userDetails = null;
    TextView txt_tool;
    @Bind(R.id.name_edit_text)
    TextInputEditText name_edit_text;
    @Bind(R.id.email_edit_text)
    TextInputEditText email_edit_text;
    @Bind(R.id.password_edit_number)
    TextInputEditText password_edit_number;
    @Bind(R.id.phone_edit_number)
    TextInputEditText phone_edit_number;
    @Bind(R.id.proceedButton)
    Button proceedButton;
    @Bind(R.id.check)
    CheckBox check;
    @Bind(R.id.changeTv)
    TextView changeTv;
    private ProgressDialog progress;
    View view;
    View context;
    CircularImageView profileIv;
    private String userChoosenTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        txt_tool = (TextView) findViewById(R.id.text_title);
        profileIv = (CircularImageView) findViewById(R.id.profileIv);
        txt_tool.setText("Registration");
        ButterKnife.bind(UserRegistration.this);
        progress = new ProgressDialog(this);
        userDetails = new ArrayList<>();
        changeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRegistration.this, MyProfile.class);
                startActivity(intent);
                finish();
            }
        });
        profileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!check.isChecked()) {
                    Toast.makeText(UserRegistration.this, "Please Accept Terms and Conditions.", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utility.isNetworkConnected(UserRegistration.this)) {
                        if (isValid()) {
                            submitregstration();
                        } else {

                        }
                    }
                }
            }
        });
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(UserRegistration.this);
        builder.setTitle("Add Photo!");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(UserRegistration.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    /*if (result)
                        cameraIntent();*/
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    /*if (result)
                        galleryIntent();*/
                } else if (items[item].equals("Cancel")) {
                    // tv_update.setVisibility(View.GONE);
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void submitregstration() {
        GitApiInterface gitApiInterface = RestClient.getClient();
        final Call<RegistrationResponse> registraionCall = gitApiInterface.postRegisterdata(name_edit_text.getText().toString(), email_edit_text.getText().toString(), password_edit_number.getText().toString(), phone_edit_number.getText().toString().trim(), PrefUtils.getInstance(UserRegistration.this).getFcmToken(), "New Delhi", "Content-Type: application/x-www-form-urlencoded");
        progress.setMessage("Please Wait .. ");
        progress.setIndeterminate(true);
        progress.show();
        registraionCall.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Response<RegistrationResponse> response) {
                Log.d("DashBoardResponse", "DashBoard Response = " + response.message());
                progress.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess() == true) {
                        GeneralUtil.SetUser_id(UserRegistration.this, String.valueOf(response.body().getUserDetails().get(0).getUserId()));
                        GeneralUtil.SetName(UserRegistration.this, name_edit_text.getText().toString());
                        GeneralUtil.Setemailid(UserRegistration.this, email_edit_text.getText().toString());
                        GeneralUtil.Setmobileno(UserRegistration.this, phone_edit_number.getText().toString());
                        GeneralUtil.SetUser_Token(UserRegistration.this, String.valueOf(response.body().getUserToken()));
                        Log.e("getUsertoken", "getUserId Response=" + response.body().getUserToken());
                        Toast.makeText(UserRegistration.this, response.body().getUserToken(), Toast.LENGTH_LONG).show();
                        ApiConstants.mobile_number = phone_edit_number.getText().toString();
                        ApiConstants.passwword = password_edit_number.getText().toString();
                        //Utility.alertMessage(UserRegistration.this, response.body().getMessage());
                        Intent intent = new Intent(UserRegistration.this, UserLoginActivity.class);
                        startActivity(intent);
                        finish();
                        //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    } else if (response.body().getSuccess() == false) {

                        Utility.alertMessage(UserRegistration.this, response.body().getMessage());
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Utility.alertMessage(UserRegistration.this, jObjError.getJSONObject("response").getString("message"));
                    } catch (Exception e) {
                        Toast.makeText(UserRegistration.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
        String email = email_edit_text.getText().toString().trim();
        String mobile = phone_edit_number.getText().toString().trim();

        if (name_edit_text.getText().toString().trim().length() == 0) {
            name_edit_text.setError(Html.fromHtml("<font color=' #E60000'>Please Enter Name</font>"));
            name_edit_text.requestFocus();

            return false;
        }
        if (email.isEmpty()) {
            email_edit_text.setError(Html.fromHtml("<font color=' #E60000'>Please enter email id</font>"));
            email_edit_text.requestFocus();
            return false;
        }
        if (!(isValidEmail(email))) {
            email_edit_text.setError(Html.fromHtml("<font color=' #E60000'>Please enter valid email id</font>"));
            email_edit_text.requestFocus();
            return false;
        }
        if (password_edit_number.getText().toString().trim().length() > 15 || password_edit_number.getText().toString().trim().length() < 6) {
            password_edit_number.setError(Html.fromHtml("<font color=' #E60000'>Password lenght must be greater than 6 or less than 15</font>"));
            password_edit_number.requestFocus();
            return false;
        }
        if (phone_edit_number.getText().toString().trim().length() > 10 || phone_edit_number.getText().toString().trim().length() < 10) {

            phone_edit_number.setError(Html.fromHtml("<font color=' #E60000'>Phone number must be greater than 0 or less than 10</font>"));
            phone_edit_number.requestFocus();
            return false;

        }


        return result;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(UserRegistration.this, UserLoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
