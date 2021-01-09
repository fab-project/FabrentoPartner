package com.app.fabrentopartner.retrofit;

import com.app.fabrentopartner.Utility.ApiConstants;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RestClientProduct {

    private static GitApiInterface gitApiInterface;
    public static String baseUrl = ApiConstants.BASE_URL_PRO;


    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okClient.interceptors().add(interceptor);
            okClient.setConnectTimeout(10, TimeUnit.MINUTES);
            okClient.setReadTimeout(10, TimeUnit.MINUTES);
            okClient.setWriteTimeout(10, TimeUnit.MINUTES);

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
