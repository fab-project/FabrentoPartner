package com.app.fabrentopartner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.fabrentopartner.Bean.BeanCreateLead.BeanCreateLeadResponce;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClientNew;
import org.json.JSONObject;
import java.util.ArrayList;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class LeadEditUpdate extends AppCompatActivity {
    EditText nameEt, ContactEt, EmailEt, AddressEt, DescriptionEt, CommentEt,  TotalAmountET, ShareAmountET;
    String name, email, contact, address, city, description, status, comment, totalamt, shareamount;
    ProgressDialog progressDialog;
    LinearLayout editLayout;
    Button proceedButton;
    TextView txt_header;
    Spinner CityEt;
    Spinner statusEt;
    String lead_id,myStatus, shareAmount, totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_edit_update);
        progressDialog = new ProgressDialog(LeadEditUpdate.this);
        txt_header = (TextView) findViewById(R.id.text_title);
        txt_header.setText("Lead Update");
        nameEt = (EditText) findViewById(R.id.nameEt);
        editLayout = (LinearLayout) findViewById(R.id.editLayout);
        ContactEt = (EditText) findViewById(R.id.ContactEt);
        EmailEt = (EditText) findViewById(R.id.EmailEt);
        AddressEt = (EditText) findViewById(R.id.AddressEt);
        //CityEt = view.findViewById(R.id.CityEt);
        CityEt = (Spinner) findViewById(R.id.CityEt);
        DescriptionEt = (EditText) findViewById(R.id.DescriptionEt);
        CommentEt = (EditText) findViewById(R.id.CommentEt);
        statusEt = (Spinner) findViewById(R.id.statusEt);
        TotalAmountET = (EditText) findViewById(R.id.TotalAmountET);
        ShareAmountET = (EditText) findViewById(R.id.ShareAmountET);
        proceedButton = (Button) findViewById(R.id.proceedButton);

        if (getIntent() != null) {
            lead_id = getIntent().getStringExtra("lead_id");
            Log.e("praveen", "lead_id" + lead_id);
            name = getIntent().getStringExtra("name");
            contact = getIntent().getStringExtra("contact");
            email = getIntent().getStringExtra("email");
            address = getIntent().getStringExtra("address");
            description = getIntent().getStringExtra("description");
            comment = getIntent().getStringExtra("comment");
            status = getIntent().getStringExtra("status");
            totalamt = getIntent().getStringExtra("totalAmount");
            shareamount = getIntent().getStringExtra("shareAmount");
            nameEt.setText(name);
            ContactEt.setText(contact);
            EmailEt.setText(email);
            AddressEt.setText(address);
            DescriptionEt.setText(description);
            CommentEt.setText(comment);
            TotalAmountET.setText(totalAmount);
            ShareAmountET.setText(shareamount);

        }
        findViewById(R.id.proceedButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameEt.getText().toString().trim();
                contact = ContactEt.getText().toString().trim();
                email = EmailEt.getText().toString().trim();
                address = AddressEt.getText().toString().trim();
                city = CityEt.getSelectedItem().toString().trim();
                description = DescriptionEt.getText().toString().trim();
                comment = CommentEt.getText().toString().trim();
                status = statusEt.getSelectedItem().toString().trim();
                totalamt = TotalAmountET.getText().toString().trim();
                shareamount = ShareAmountET.getText().toString().trim();
                final ArrayList<String> strStatus = new ArrayList<>();
                strStatus.add("Select Status");
                strStatus.add("Pending");
                strStatus.add("Converted");
                strStatus.add("Hot");
                strStatus.add("Cold");
                strStatus.add("Allocated");
                statusEt.setAdapter(new ArrayAdapter<String>(LeadEditUpdate.this, android.R.layout.simple_spinner_dropdown_item, strStatus));
                statusEt.setSelection(0);
                statusEt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        status = statusEt.getSelectedItem().toString().trim();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                final ArrayList<String> strDeviceArr = new ArrayList<>();
                strDeviceArr.add("Select City");
                strDeviceArr.add("Delhi");
                strDeviceArr.add("Gurugram");
                strDeviceArr.add("Ghaziabad");
                strDeviceArr.add("Noida");
                strDeviceArr.add("Faridabad");
                strDeviceArr.add("Mumbai");
                strDeviceArr.add("Bangalore");
                strDeviceArr.add("Chandigarh");
                strDeviceArr.add("Pune");
                CityEt.setAdapter(new ArrayAdapter<String>(LeadEditUpdate.this, android.R.layout.simple_spinner_dropdown_item, strDeviceArr));
                CityEt.setSelection(0);
                CityEt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        city = CityEt.getSelectedItem().toString().trim();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                if (name.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter lead name", Toast.LENGTH_SHORT).show();
                } else if (contact.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your lead phone number", Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter full lead email address", Toast.LENGTH_SHORT).show();
                } else if (!Utility.isValidEmailId(email)) {
                    Toast.makeText(LeadEditUpdate.this, "Please enter valid lead email address", Toast.LENGTH_SHORT).show();
                } else if (address.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your lead address", Toast.LENGTH_SHORT).show();
                } else if (city.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your lead city", Toast.LENGTH_SHORT).show();
                } else if (description.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your description", Toast.LENGTH_SHORT).show();
                } else if (comment.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your comment", Toast.LENGTH_SHORT).show();
                } else if (status.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your status", Toast.LENGTH_SHORT).show();
                } else if (totalamt.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your total amount", Toast.LENGTH_SHORT).show();
                } else if (shareamount.equals("")) {
                    Toast.makeText(LeadEditUpdate.this, "Enter your share amount", Toast.LENGTH_SHORT).show();
                } else {
                    if (Utility.isNetworkConnected(LeadEditUpdate.this)) {
                        createLead(name, contact, email, address, city, description, comment, totalamt, shareamount);
                    } else {
                        Utility.alertMessage(LeadEditUpdate.this, getString(R.string.noiternet));
                    }
                }
            }
        });
    }

    private void createLead(String name, String contact, String email, String address, String city, String description, String comment, String totalamt, String shareamount) {
        GitApiInterface gitApiInterface = RestClientNew.getClient(LeadEditUpdate.this);
        Call<BeanCreateLeadResponce> registraionCall = gitApiInterface.postLeadUpdatedata(lead_id, name, contact, email, address, city, description, comment, totalamt, status, shareamount, "Content-Type: application/x-www-form-urlencoded");
        progressDialog.setMessage("Please Wait .. ");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        registraionCall.enqueue(new Callback<BeanCreateLeadResponce>() {
            @Override
            public void onResponse(Response<BeanCreateLeadResponce> response) {
                Log.d("DashBoardResponse", "DashBoard Response=" + response.message());
                progressDialog.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess()) {
                        Log.d("MySuccess", "getSuccess" + response.message());

                        myStatus=response.body().getDetails().getLeadStatus();
                        Log.d("myStatus", "Hello" + myStatus);

                        Utility.alertMessage(LeadEditUpdate.this, response.body().getMessage());
                        finish();
                    } else if (response.body().getSuccess() == false) {
                        Utility.alertMessage(LeadEditUpdate.this, response.body().getMessage());
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Utility.alertMessage(LeadEditUpdate.this, jObjError.getJSONObject("response").getString("message"));
                    } catch (Exception e) {
                        Toast.makeText(LeadEditUpdate.this, e.getMessage(), Toast.LENGTH_LONG).show();
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
}
