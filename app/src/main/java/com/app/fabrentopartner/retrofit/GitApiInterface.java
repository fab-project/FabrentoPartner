package com.app.fabrentopartner.retrofit;

import com.app.fabrentopartner.Bean.BeaProductCat;
import com.app.fabrentopartner.Bean.BeanAllProduct;
import com.app.fabrentopartner.Bean.BeanBarChart.BarChartResponce;
import com.app.fabrentopartner.Bean.BeanCreateLead.BeanCreateLeadResponce;
import com.app.fabrentopartner.Bean.BeanLoginRegis.RegistrationResponse;
import com.app.fabrentopartner.Bean.BeanProduct;
import com.app.fabrentopartner.Bean.BeanSearchResult;
import com.app.fabrentopartner.Bean.BeanUserLogin;
import com.app.fabrentopartner.Bean.UpdateResponse;
import com.app.fabrentopartner.BeanFetchLeadData.FetchLeadResponce;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface GitApiInterface {

    @POST("app_main_listing")
    @FormUrlEncoded
    Call<BeanAllProduct> getAllProduct(@Field("store") String store, @Field("type") String type);

    @POST("app_category_listing")
    @FormUrlEncoded
    Call<BeaProductCat> productcatlist(@Field("store") String store, @Field("category") String category);

    @POST("app_product_listing")
    @FormUrlEncoded
    Call<BeanProduct> productList(@Field("store") String store,
                                  @Field("category") String category,
                                  @Field("sub_category") String sub_category,
                                  @Field("sub_sub_category") String sub_sub_category,
                                  @Field("minDeposit") String minDeposit,
                                  @Field("maxDeposit") String maxDeposit,
                                  @Field("monthly_rental") String monthly_rental,
                                  @Field("shortBy") String shortBy,
                                  @Field("userID") String userID,
                                  @Field("page") String page,
                                  @Field("client_type") String client_type);

    @POST("app_search")
    @FormUrlEncoded
    Call<BeanSearchResult> getSearchResult(@Field("store") String store, @Field("search_key") String search_key, @Field("client_type") String client_type);

    @POST("signup_user")
    @FormUrlEncoded
    Call<RegistrationResponse> postRegisterdata(@Field("user_name") String user_name,
                                                @Field("user_email") String user_email,
                                                @Field("user_password") String user_password,
                                                @Field("user_phone") String user_phone,
                                                @Field("user_fcm") String user_fcm,
                                                @Field("user_city") String user_city,
                                                @Field("headers") String headers);

    @POST("update_user")
    @FormUrlEncoded
    Call<UpdateResponse> postUpdatedata(@Field("user_name") String user_name,
                                        @Field("user_id") String user_id,
                                        @Field("user_city") String user_city,
                                        @Field("headers") String headers);


    @POST("login_user")
    @FormUrlEncoded
    Call<BeanUserLogin> postLoginData(@Field("user_email") String user_email,
                                      @Field("user_password") String user_password,
                                       @Field("headers") String headers);

    @POST("create_lead")
    @FormUrlEncoded
    Call<BeanCreateLeadResponce> postcreateLeaddata(@Field("user_id") String user_id,
                                                    @Field("lead_name") String user_name,
                                                    @Field("lead_contact") String lead_contact,
                                                    @Field("lead_email") String user_city,
                                                    @Field("lead_address") String lead_address,
                                                    @Field("lead_city") String lead_city,
                                                    @Field("lead_description") String lead_description,
                                                    @Field("lead_comment") String lead_comment,
                                                    @Field("lead_total_amount") String lead_total_amount,
                                                    @Field("lead_share_amount") String lead_share_amount,
                                                    @Field("headers") String headers);


    @POST("update_lead")
    @FormUrlEncoded
    Call<BeanCreateLeadResponce> postLeadUpdatedata(@Field("lead_id") String lead_id,
                                                    @Field("lead_name") String user_name,
                                                    @Field("lead_contact") String lead_contact,
                                                    @Field("lead_email") String lead_email,
                                                    @Field("lead_address") String lead_address,
                                                    @Field("lead_city") String lead_city,
                                                    @Field("lead_description") String lead_description,
                                                    @Field("lead_comment") String lead_comment,
                                                    @Field("lead_total_amount") String lead_total_amount,
                                                    @Field("lead_status") String lead_status,
                                                    @Field("lead_share_amount") String lead_share_amount,
                                                    @Field("headers") String headers);

    @POST("read_lead")
    @FormUrlEncoded
    Call<FetchLeadResponce> postfetchLeaddata(@Field("lead_status") String lead_status,
                                              @Field("user_id") String user_id,
                                              @Field("headers") String headers);

    @POST("mis")
    @FormUrlEncoded
    Call<BarChartResponce> postbarchart(@Field("created_by") String created_by,
                                             @Field("start_date") String start_date,
                                             @Field("end_date") String end_date,
                                              @Field("headers") String headers);

}