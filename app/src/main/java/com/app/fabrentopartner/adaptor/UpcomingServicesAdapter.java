package com.app.fabrentopartner.adaptor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.Bean.BeanCreateLead.BeanCreateLeadResponce;
import com.app.fabrentopartner.Bean.BeanCreateLead.CreateLeadDetails;
import com.app.fabrentopartner.BeanFetchLeadData.BeanFetchDetail;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.UserRegistration;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.app.fabrentopartner.Utility.PrefUtils;
import com.app.fabrentopartner.Utility.Utility;
import com.app.fabrentopartner.retrofit.GitApiInterface;
import com.app.fabrentopartner.retrofit.RestClientNew;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;


public class UpcomingServicesAdapter extends RecyclerView.Adapter<UpcomingServicesAdapter.ViewHolder> {
    private static final String TAG = UpcomingServicesAdapter.class.getSimpleName();
    String name, email, contact, address, city, description, comment, totalamt, shareamount;
    Context context;
    private CreateLeadDetails details;
    ProgressDialog progressDialog;
    Activity activity;
    String Status_id;


    public UpcomingServicesAdapter(Context context) {
        this.context = context;


    }


    @Override
    public UpcomingServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_lead, parent, false);
        return new UpcomingServicesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        EditText nameEt, ContactEt, EmailEt, AddressEt, DescriptionEt, CommentEt, TotalAmountET, ShareAmountET;
        Button proceedButton;

        Spinner CityEt;

        public ViewHolder(final View itemView) {
            super(itemView);
            progressDialog = new ProgressDialog(itemView.getContext());

            nameEt = itemView.findViewById(R.id.nameEt);
            ContactEt = itemView.findViewById(R.id.ContactEt);
            EmailEt = itemView.findViewById(R.id.EmailEt);
            AddressEt = itemView.findViewById(R.id.AddressEt);
            //CityEt = view.findViewById(R.id.CityEt);
            CityEt = itemView.findViewById(R.id.CityEt);
            DescriptionEt = itemView.findViewById(R.id.DescriptionEt);
            CommentEt = itemView.findViewById(R.id.CommentEt);
            TotalAmountET = itemView.findViewById(R.id.TotalAmountET);
            ShareAmountET = itemView.findViewById(R.id.ShareAmountET);
            proceedButton = itemView.findViewById(R.id.proceedButton);
            itemView.findViewById(R.id.proceedButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    name = nameEt.getText().toString().trim();
                    contact = ContactEt.getText().toString().trim();
                    email = EmailEt.getText().toString().trim();
                    address = AddressEt.getText().toString().trim();
                    city = CityEt.getSelectedItem().toString().trim();
                    description = DescriptionEt.getText().toString().trim();
                    comment = CommentEt.getText().toString().trim();
                    totalamt = TotalAmountET.getText().toString().trim();
                    shareamount = ShareAmountET.getText().toString().trim();
                    nameEt.setText("");
                    ContactEt.setText("");
                    EmailEt.setText("");
                    AddressEt.setText("");
                    DescriptionEt.setText("");
                    CommentEt.setText("");
                    TotalAmountET.setText("");
                    ShareAmountET.setText("");
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
                    CityEt.setAdapter(new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, strDeviceArr));
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
                        Toast.makeText(itemView.getContext(), "Enter lead name", Toast.LENGTH_SHORT).show();

                    } else if (contact.equals("")) {
                        Toast.makeText(itemView.getContext(), "Enter your lead phone number", Toast.LENGTH_SHORT).show();
                    } else if (email.equals("")) {
                        Toast.makeText(itemView.getContext(), "Enter full lead email address", Toast.LENGTH_SHORT).show();
                    } else if (!Utility.isValidEmailId(email)) {
                        Toast.makeText(itemView.getContext(), "Please enter valid lead email address", Toast.LENGTH_SHORT).show();
                    } else if (address.equals("")) {
                        Toast.makeText(itemView.getContext(), "Enter your lead address", Toast.LENGTH_SHORT).show();
                    } else if (city.equals("")) {
                        Toast.makeText(itemView.getContext(), "Enter your lead city", Toast.LENGTH_SHORT).show();
                    } else if (description.equals("")) {
                        Toast.makeText(itemView.getContext(), "Enter your description", Toast.LENGTH_SHORT).show();
                    } else if (comment.equals("")) {
                        Toast.makeText(itemView.getContext(), "Enter your comment", Toast.LENGTH_SHORT).show();
                    } else if (totalamt.equals("")) {
                        Toast.makeText(activity, "Enter your total amount", Toast.LENGTH_SHORT).show();
                    } else if (shareamount.equals("")) {
                        Toast.makeText(itemView.getContext(), "Enter your share amount", Toast.LENGTH_SHORT).show();
                    } else {
                        if (Utility.isNetworkConnected((Activity) itemView.getContext())) {
                            createLead(name, contact, email, address, city, description, comment, totalamt, shareamount);
                        }
                    }
                }
            });
        }
    }

    private void createLead(String name, String contact, String email, String address, String city, String description, String comment, String totalamt, String shareamount) {
        GitApiInterface gitApiInterface = RestClientNew.getClient(activity);
       final Call<BeanCreateLeadResponce> registraionCall = gitApiInterface.postcreateLeaddata(PrefUtils.getInstance(context).getKeyUser(), name, contact, email, address, city, description, comment, totalamt, shareamount, "Content-Type: application/x-www-form-urlencoded");
        progressDialog.setMessage("Please Wait .. ");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        registraionCall.enqueue(new Callback<BeanCreateLeadResponce>() {
            @Override
            public void onResponse(Response<BeanCreateLeadResponce> response) {
                Log.d("DashBoardResponse", "DashBoard Response = " + response.message());
                progressDialog.dismiss();
                if (response.isSuccess()) {
                    if (response.body().getSuccess()) {
                        Log.d("MySuccess", "getSuccess" + response.message());
                        Status_id = String.valueOf(response.body().getDetails().getLeadStatus());
                        Log.d("Status_id", "Status_id" + Status_id);
                        PrefUtils.getInstance(context).setIsStatus(String.valueOf(response.body().getDetails().getLeadStatus()));
                        Utility.alertMessage(context, response.body().getMessage());

                    } else if (response.body().getSuccess() == false) {

                        Utility.alertMessage(context, response.body().getMessage());
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
                progressDialog.dismiss();
            }
        });
    }
}
