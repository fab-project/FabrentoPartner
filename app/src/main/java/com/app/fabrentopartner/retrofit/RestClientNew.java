package com.app.fabrentopartner.retrofit;

import android.content.Context;
import android.util.Log;

import com.app.fabrentopartner.Utility.ApiConstants;
import com.app.fabrentopartner.Utility.GeneralUtil;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestClientNew {
    private static GitApiInterface gitApiInterface;
    public static String baseUrl = ApiConstants.BASE_URL;

    public static GitApiInterface getClient(final Context mContext) {
        if (gitApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClient.interceptors().add(interceptor);
            okClient.setConnectTimeout(10, TimeUnit.MINUTES);
            okClient.setReadTimeout(10, TimeUnit.MINUTES);
            okClient.setWriteTimeout(10, TimeUnit.MINUTES);
            Log.d("token", "Bearer "+ GeneralUtil.GetUser_Token(mContext));
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "fabpartner"+"#eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIxMTFoZDUxNTZoa2ZmM2l2djMiLCJpYXQiOjE2MDA4NDc5MjksImV4cCI6MTYzMjM4MzkyOX0.Za1iz4shoqkE8g_orntQdmJudERWGy7hxBrNBt7cISc")
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }
            });

        /*   okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });*/



            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
//                    .addConverter(String.class, new ToStringConverter())////with jsonobject
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface;
    }
}
