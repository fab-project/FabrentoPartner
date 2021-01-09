package com.app.fabrentopartner.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.Bean.SearchList;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.Utility.ApiConstants;
import com.bumptech.glide.Glide;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.HolderEstateManager> {

    Context context;
    List<SearchList> dashboarddatalist;


    public SearchAdapter(Context context, List<SearchList> dashboarddatalist) {
        this.context = context;
        this.dashboarddatalist = dashboarddatalist;
    }

    @Override
    public SearchAdapter.HolderEstateManager onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.productrecomded, parent, false);
        SearchAdapter.HolderEstateManager holderEstateManager = new SearchAdapter.HolderEstateManager(view);
        return holderEstateManager;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.HolderEstateManager holder, final int position) {
        if (dashboarddatalist.get(position).getProductType() == "0") {
            Glide.with(context).load(ApiConstants.Image_Search_Package + dashboarddatalist.get(position).getImage()).error(R.drawable.no_media).into(holder.productimage);
            holder.tvprice.setText("₹ " + dashboarddatalist.get(position).getMonthlyRental() + "/mo");
            holder.tvproductname.setText(dashboarddatalist.get(position).getTitle());

        } else {
            Glide.with(context).load(ApiConstants.Image_Product_Search + dashboarddatalist.get(position).getImage()).error(R.drawable.no_media).into(holder.productimage);
            holder.tvprice.setText("₹ " + dashboarddatalist.get(position).getMonthlyRental() + "/mo");
            holder.tvproductname.setText(dashboarddatalist.get(position).getTitle());

        }
        //   Picasso.with(context).load(ApiConstants.package_path + dashboarddatalist.get(position).getImage()).error(R.drawable.no_media).into(holder.cartimage);
       /* holder.lvseacrch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("position", dashboarddatalist.get(position).getSlug());
                intent.putExtra("productname", String.valueOf(dashboarddatalist.get(position).getTitle()));
                context.startActivity(intent);
            }
        });*/
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
        ImageView productimage;
        TextView tvproductname, tvprice;
        LinearLayout lvseacrch;

        public HolderEstateManager(View itemView) {
            super(itemView);
            productimage = (ImageView) itemView.findViewById(R.id.productimage);
            tvproductname = (TextView) itemView.findViewById(R.id.tvproductname);
            tvprice = (TextView) itemView.findViewById(R.id.tvprice);
            lvseacrch = (LinearLayout) itemView.findViewById(R.id.lvseacrch);
        }
    }
}
