package com.app.fabrentopartner.adaptor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.Bean.CatList;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.Utility.ApiConstants;
import com.app.fabrentopartner.retrofit.BtnClickListener;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecycleviewTopCatAdapter extends RecyclerView.Adapter<RecycleviewTopCatAdapter.HolderEstateManager> {
    private BtnClickListener mClickListener = null;
    private int row_index;
    List<CatList> catlist;
    Context context;

    public RecycleviewTopCatAdapter(Context context, List<CatList> catlist, int lastPosition,  BtnClickListener mClickListener) {
        this.context = context;
        this.catlist = catlist;
        this.row_index = lastPosition;
        this.mClickListener = mClickListener;
    }

    @Override
    public RecycleviewTopCatAdapter.HolderEstateManager onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productlistcat, parent, false);
        RecycleviewTopCatAdapter.HolderEstateManager holderEstateManager = new RecycleviewTopCatAdapter.HolderEstateManager(view);
        return holderEstateManager;
    }

    @Override
    public void onBindViewHolder(RecycleviewTopCatAdapter.HolderEstateManager holder, final int position) {
        //holder.lvproduct.setAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_left_to_right));
        try {
            Picasso.get().load(ApiConstants.Image_Base_product_Url + catlist.get(position).getImage()).error(R.drawable.no_media).into(holder.ivproduct);
            holder.tvproduct.setText(catlist.get(position).getName());
            holder.lvproduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();
                    mClickListener.onBtnClick(catlist.get(position).getSlug());
                }
            });
            if (row_index == position) {
                holder.tvproduct.setTextColor(Color.parseColor("#F44336"));
            }    else {
                holder.tvproduct.setTextColor(Color.parseColor("#000000"));
            }

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return catlist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class HolderEstateManager extends RecyclerView.ViewHolder {
        ImageView ivproduct;
        TextView tvproduct;
        LinearLayout lvproduct;
        LinearLayout llProductListCat;

        public HolderEstateManager(View itemView) {
            super(itemView);
            llProductListCat = (LinearLayout) itemView.findViewById(R.id.llProductListCat);
            ivproduct = (ImageView) itemView.findViewById(R.id.ivproduct);
            tvproduct = (TextView) itemView.findViewById(R.id.tvproductname);
            lvproduct = (LinearLayout) itemView.findViewById(R.id.lvproduct);
        }
    }
}
