package com.yd.httpmudule;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitService {

    private static Retrofit retrofit = null;
    private static Retrofit.Builder retrofitBuilder = null;
    private static OkHttpClient okHttpClient = null;

    /**
     * 可以自定义retrofit
     */
    public static void setRetrofitBuilder(Retrofit.Builder retrofitBuilder) {
        RetrofitService.retrofitBuilder = retrofitBuilder;
    }

    /**
     * 可以自定义OkHttpClient
     */
    public static void setOkHttpClient(OkHttpClient oc) {
        okHttpClient = oc;
    }


    public static <T> T createService(Class<T> serviceClass) {
        OkHttpClient thisOkHttpClient = okHttpClient != null ? okHttpClient : OkHttp.getInstance().getHttpClient();
        if (retrofit == null) {
            if (retrofitBuilder != null) {
                retrofit = retrofitBuilder.client(thisOkHttpClient).build();
            } else {
                if (getRetrofitBuilder() != null) {
                    retrofit = getRetrofitBuilder().client(thisOkHttpClient).build();
                }
            }
        }
        return retrofit.create(serviceClass);
    }


    private static Retrofit.Builder getRetrofitBuilder() {
        if (retrofitBuilder == null) {
            retrofitBuilder = new Retrofit.Builder()
                    .baseUrl("http://www.xxxx.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create());
        }
        return retrofitBuilder;
    }

}