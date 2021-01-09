package com.app.fabrentopartner.adaptor;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.fabrentopartner.Bean.Datum;
import com.app.fabrentopartner.R;
import com.app.fabrentopartner.Utility.ApiConstants;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.app.fabrentopartner.Utility.PrefUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductRecycleAdapter extends RecyclerView.Adapter<ProductRecycleAdapter.GalleryRecyclerViewHolder> {
    List<Datum> resultSetList = new ArrayList<>();
    List<Datum> resultSetList1;
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private boolean isLoadingAdded = false;
    Activity mActivity;
    String pSlug;


    public ProductRecycleAdapter(List<Datum> resultSetList, Activity activity) {
        this.resultSetList = resultSetList;
        this.mActivity = activity;

    }

    @Override
    public GalleryRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_gridview, null);
                GalleryRecyclerViewHolder rcv = new GalleryRecyclerViewHolder(layoutView);
                return rcv;
            case VIEW_TYPE_LOADING:
                View layoutnewView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
                GalleryRecyclerViewHolder rcvs = new GalleryRecyclerViewHolder(layoutnewView);
                return rcvs;
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(final GalleryRecyclerViewHolder holder, final int position) {
        final Datum result = resultSetList.get(position);
        switch (getItemViewType(position)) {
            case VIEW_TYPE_NORMAL:
                final GalleryRecyclerViewHolder holder1 = (GalleryRecyclerViewHolder) holder;
                // holder.lvproduct.setAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.item_animation_fall_down));
                holder1.tvproductdetail.setText(result.getTitle());

                pSlug = resultSetList.get(position).getSlug();
                System.out.println("get_pSlug" + pSlug);
                holder1.ll_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        shareYourProducts(position);
                        Toast.makeText(mActivity, position + " Share Item", Toast.LENGTH_SHORT).show();
                    }
                });
               /* Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "fonts/Roboto-Bold.ttf");
                holder.tvproductdetail.setTypeface(font);*/
                holder1.actualprice.setText("₹ " + result.getMonthlyRental() + "/Mo");
                System.out.println("get_actuals_price" + result.getMonthlyRental());
                if (result.getDiscount_price() == 0) {
                    holder1.tvpricee.setVisibility(View.GONE);
                } else {
                    holder1.tvpricee.setVisibility(View.VISIBLE);
                }
                holder1.tvpricee.setText("₹ " + result.getDiscount_price() + "/Mo");
                holder1.tvpricee.setPaintFlags(holder1.tvpricee.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                System.out.println("check_stiker" + result.getStitle());
               /* if (!TextUtils.isEmpty(result.getStitle())) {
                    holder1.lvsticker.setVisibility(View.VISIBLE);
                    if (result.getStitle().equalsIgnoreCase("Sale")) {
                        holder1.tv_salestiker.setVisibility(View.GONE);
                        holder1.tvsticker.setVisibility(View.VISIBLE);
                        holder1.tvsticker.setText(result.getStitle().toUpperCase());
                        holder1.tvsticker.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.stiker_circle));
                    } else if (result.getStitle().equalsIgnoreCase("featured")) {
                        holder1.tv_salestiker.setVisibility(View.VISIBLE);
                        holder1.tvsticker.setVisibility(View.GONE);
                        holder1.tv_salestiker.setText(result.getStitle().toUpperCase());
                        holder1.tv_salestiker.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.sticker_porpal_add));
                    } else if (result.getStitle().equalsIgnoreCase("popular")) {
                        holder1.tv_salestiker.setVisibility(View.VISIBLE);
                        holder1.tvsticker.setVisibility(View.GONE);
                        holder1.tv_salestiker.setText(result.getStitle().toUpperCase());
                        holder1.tv_salestiker.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.sticker_diffrent_add));
                    } else {
                        holder1.tv_salestiker.setVisibility(View.VISIBLE);
                        holder1.tvsticker.setVisibility(View.GONE);
                        holder1.tv_salestiker.setText(result.getStitle().toUpperCase());
                        holder1.tv_salestiker.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.sticker_add));
                    }

                } else {
                    holder1.lvsticker.setVisibility(View.GONE);
                }*/

        }
        try {
            Glide.with(mActivity).load(ApiConstants.Image_Base_Product_List_Url + resultSetList.get(position).getImage()).error(R.drawable.no_media).into(holder.imgproduct);

        } catch (Exception e) {
            e.printStackTrace();
        }

       /* holder.imgproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, ProductDetailActivity.class);
                i.putExtra("position", resultSetList.get(position).getSlug());
                i.putExtra("productname", resultSetList.get(position).getTitle());
                mActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                mActivity.startActivity(i);

            }
        });*/

    }

    private void shareYourProducts(int position) {
        try {
            String name = "Fabrento lets you rent best quality furniture at affordable rent. Please follow this link to order your preferred product:" + "\n";
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, name);//GeneralUtil.GetCity(mActivity)
            String app_url = name + "" + "https://www.fabrento.com/" + "NewDelhi" + "/products/" + resultSetList.get(position).getSlug() + "/" + "?utm_source=" + GeneralUtil.Get_AffiliateCode_id(mActivity) + "&" + "utm_content=for_affiliates";
            //System.out.println("app_url: " + app_url);
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, app_url);
            mActivity.startActivity(Intent.createChooser(shareIntent, "Share via"));
        } catch (ActivityNotFoundException e) {
            e.fillInStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == resultSetList.size() - 1 && isLoadingAdded ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }


    public void add(Datum datum) {
        resultSetList.add(datum);
        notifyItemInserted(resultSetList.size());
    }

    public void addAll(List<Datum> postItems) {
        for (Datum datum : postItems) {
            add(datum);
        }
    }

    public void addBottomItem() {
        add(new Datum());
    }

    public void removeLastEmptyItem() {
        int position = resultSetList.size() - 1;
        Datum item = getItem(position);
        if (item != null) {
            resultSetList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = resultSetList.size() - 1;
        Datum item = getItem(position);
        if (item != null) {
            resultSetList.remove(position);
            notifyItemRemoved(position);
        }
    }

    private Datum getItem(int position) {
        return resultSetList.get(position);
    }


    @Override
    public int getItemCount() {
        return resultSetList == null ? 0 : resultSetList.size();
    }

   /* public void addItems(List<Datum> postItems) {
        resultSetList.addAll(postItems);
        notifyDataSetChanged();
    }*/

    /* public void addLoading() {
         isLoaderVisible = true;
         resultSetList.add(new Datum());
         notifyItemInserted(resultSetList.size() - 1);
     }

     public void removeLoading() {
         isLoaderVisible = false;
         int position = resultSetList.size() - 1;
         Datum item = getItem(position);
         if (item != null) {
             resultSetList.remove(position);
             notifyItemRemoved(position);
         }
     }*/
    public void listclean() {
        if (!resultSetList.isEmpty()) {
            resultSetList.clear();
        }

    }

    public void clear() {
        resultSetList.clear();
        notifyDataSetChanged();
    }

    public class GalleryRecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView tvproductdetail, actualprice, discountprice, discount, tvpricee, tv_salestiker, tvsticker, tvresticker;
        public LinearLayout addtowish, addtobag, lvresticker, lvsticker, lvproduct, ll_share;
        public ImageView imgproduct;
        private ProgressBar progressBar;

        public GalleryRecyclerViewHolder(View itemView) {
            super(itemView);
            tvproductdetail = (TextView) itemView.findViewById(R.id.tvproductdetail);
            actualprice = (TextView) itemView.findViewById(R.id.actualprice);
            tvpricee = (TextView) itemView.findViewById(R.id.tvpricee);
            tvsticker = (TextView) itemView.findViewById(R.id.tvsticker);
            lvsticker = (LinearLayout) itemView.findViewById(R.id.lvsticker);
            ll_share = (LinearLayout) itemView.findViewById(R.id.ll_share);
            // discountprice = (TextView) itemView.findViewById(R.id.discountprice);
            // discount = (TextView) itemView.findViewById(R.id.discount);
            addtowish = (LinearLayout) itemView.findViewById(R.id.addtowish);
            addtobag = (LinearLayout) itemView.findViewById(R.id.addtobag);
            lvproduct = (LinearLayout) itemView.findViewById(R.id.lvproduct);
            // lvresticker = (LinearLayout) itemView.findViewById(R.id.lvresticker);
            // tvresticker = (TextView) itemView.findViewById(R.id.tvresticker);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            imgproduct = (ImageView) itemView.findViewById(R.id.imgproduct);
            tv_salestiker = (TextView) itemView.findViewById(R.id.tv_salestiker);


        }
    }

    public class ProgressHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public ProgressHolder(View itemView) {
            super(itemView);


        }


    }

}

