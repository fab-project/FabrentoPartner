package com.app.fabrentopartner.adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.BeanFetchLeadData.BeanFetchDetail;
import com.app.fabrentopartner.LeadEditUpdate;
import com.app.fabrentopartner.R;

import java.util.List;


public class PreviousServicesAdapter extends RecyclerView.Adapter<PreviousServicesAdapter.ViewHolder> {
    private static final String TAG = PreviousServicesAdapter.class.getSimpleName();

    List<BeanFetchDetail> mDataset;
    BeanFetchDetail idBean;
    String lead_id;
    Context context;

    public PreviousServicesAdapter(Context context, List<BeanFetchDetail> mDataset) {
        this.context = context;
        this.mDataset = mDataset;

    }

    @Override
    public PreviousServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_lead, parent, false);
        PreviousServicesAdapter.ViewHolder vh = new PreviousServicesAdapter.ViewHolder(view);
        return vh;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final PreviousServicesAdapter.ViewHolder holder, final int position) {
        idBean = mDataset.get(position);
        holder.txt_name.setText(mDataset.get(position).getLeadName());
        holder.txt_city.setText(mDataset.get(position).getLeadCity());
        holder.txt_contact.setText(mDataset.get(position).getLeadContact());
        holder.txt_req.setText(mDataset.get(position).getLeadDescription());
        holder.txt_status.setText(mDataset.get(position).getLeadStatus());
        String status = mDataset.get(position).getLeadStatus();
        lead_id = mDataset.get(position).getLeadId();
        Log.d("leaddata", "lead_id" + lead_id);


        if (status.equalsIgnoreCase("Pending")) {
            holder.txt_status.setTextColor(Color.parseColor("#C62828"));
        }
        if (status.equalsIgnoreCase("Converted")) {
            holder.txt_status.setTextColor(Color.parseColor("#66BB6A"));
        }
        if (status.equalsIgnoreCase("Hot")) {
            holder.txt_status.setTextColor(Color.parseColor("#F9A825"));
        }
        if (status.equalsIgnoreCase("Cold")) {
            holder.txt_status.setTextColor(Color.parseColor("#29B6F6"));
        }
        if (status.equalsIgnoreCase("Allocated")) {
            holder.txt_status.setTextColor(Color.parseColor("#7E57C2"));
        }
        holder.txt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LeadEditUpdate.class);
                intent.putExtra("lead_id", lead_id);
                intent.putExtra("name", mDataset.get(position).getLeadName());
                intent.putExtra("contact", mDataset.get(position).getLeadContact());
                intent.putExtra("email", mDataset.get(position).getLeadEmail());
                intent.putExtra("address", mDataset.get(position).getLeadAddress());
                intent.putExtra("description", mDataset.get(position).getLeadDescription());
                intent.putExtra("comment", mDataset.get(position).getLeadComment());
                intent.putExtra("status", mDataset.get(position).getLeadStatus());
                intent.putExtra("totalAmount", mDataset.get(position).getLeadTotalAmount());
                intent.putExtra("shareAmount", mDataset.get(position).getLeadShareAmount());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name, txt_city, txt_allo, txt_contact, txt_req, txt_status;
        ImageView txt_edit;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_city = (TextView) itemView.findViewById(R.id.txt_city);
            txt_allo = (TextView) itemView.findViewById(R.id.txt_allo);
            txt_contact = (TextView) itemView.findViewById(R.id.txt_contact);
            txt_req = (TextView) itemView.findViewById(R.id.txt_req);
            txt_status = (TextView) itemView.findViewById(R.id.txt_status);
            txt_edit = (ImageView) itemView.findViewById(R.id.txt_edit);
        }
    }
}
