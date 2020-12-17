package com.yd.ydyun.websocket;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class OkHttpClientInstance {
    private static OkHttpClient client = null;
    private static OkHttpClientInstance instance=null;

    public static OkHttpClientInstance getInstance() {
        if (instance == null){
            synchronized (OkHttpClientInstance.class) {
                instance = new OkHttpClientInstance();
            }

        }
        return instance;
    }
    public OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
//                    .pingInterval(10,TimeUnit.SECONDS)
                    .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                    .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                    .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                    .build();
        }
        return client;
    }
}
