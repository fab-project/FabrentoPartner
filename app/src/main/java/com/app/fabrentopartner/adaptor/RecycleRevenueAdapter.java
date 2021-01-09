package com.app.fabrentopartner.adaptor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.R;

public class RecycleRevenueAdapter extends RecyclerView.Adapter<RecycleRevenueAdapter.ViewHolder> {
    private static final String TAG = RecycleRevenueAdapter.class.getSimpleName();
    Context context;
   /* public String[] mColors = {
            "3F51B5","FF9800","009688","673AB7"
    };*/
    public RecycleRevenueAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecycleRevenueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_revenue, parent, false);
        return new RecycleRevenueAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

       /* String color="#"+mColors[position];
        for(int c=1;c<mColors.length;c++)
        {

            holder.lead_layout.setBackgroundColor(Color.parseColor(color));

            if(c==mColors.length)
            {
                c=1;
            }*/
        //}
       /* if (type.equals("active")) {
            holder.datetime.setTextColor(Color.parseColor("#F0B91D"));
            holder.imageView1.setImageResource(R.drawable.ic_right_arrow);
        } else {
            holder.datetime.setTextColor(Color.parseColor("#9B9B9B"));
            holder.imageView1.setImageResource(R.drawable.ic_right_arrow_black);
        }*/
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView Lead_name;
        LinearLayout lead_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            Lead_name = (TextView) itemView.findViewById(R.id.Lead_name);
            lead_layout = (LinearLayout) itemView.findViewById(R.id.lead_layout);

        }
    }
}

