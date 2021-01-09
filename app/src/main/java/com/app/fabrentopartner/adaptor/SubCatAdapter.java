package com.app.fabrentopartner.adaptor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.Bean.CategoryList;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.retrofit.SubClickListener;

import java.util.List;

public class SubCatAdapter extends RecyclerView.Adapter<SubCatAdapter.HolderEstateManager> {
    Context context;
    List<CategoryList> dashboarddatalist;
    int row_index;
    private SubClickListener subClickListener = null;

    public SubCatAdapter(Context context, List<CategoryList> dashboarddatalist, int position, SubClickListener subClickListener) {
        this.context = context;
        this.dashboarddatalist = dashboarddatalist;
        this.row_index = position;
        this.subClickListener = subClickListener;
    }

    @Override
    public SubCatAdapter.HolderEstateManager onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chiplayout, parent, false);
        SubCatAdapter.HolderEstateManager holderEstateManager = new SubCatAdapter.HolderEstateManager(view);
        return holderEstateManager;
    }

    @Override
    public void onBindViewHolder(SubCatAdapter.HolderEstateManager holder, final int position) {
        // holder.lvback.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_left_to_right));

        System.out.println("check_list" + dashboarddatalist.get(position).getImage());
        // Picasso.get().load(ApiConstants.Image_Base_Sub_Cat_URl + dashboarddatalist.get(position).getImage()).error(R.drawable.no_media).into(holder.subcatimage);
        holder.tvname.setText(dashboarddatalist.get(position).getName());

        holder.lvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
                subClickListener.onSubClick(dashboarddatalist.get(position).getId().toString());
            }
        });

        if(row_index==position){
            holder.tvname.setBackgroundResource(R.drawable.textbackground);
            holder.tvname.setTextColor(Color.parseColor("#ffffff"));
        }
        else {
            holder.tvname.setBackgroundResource(R.drawable.textviewwhitebackgriundred);
            holder.tvname.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        return dashboarddatalist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class HolderEstateManager extends RecyclerView.ViewHolder {
        TextView tvname;
        ImageView subcatimage;
        LinearLayout lvback;

        public HolderEstateManager(View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.action_chip);
            // subcatimage = (ImageView) itemView.findViewById(R.id.subcatimage);
            lvback = (LinearLayout) itemView.findViewById(R.id.lvback);
        }
    }
}
