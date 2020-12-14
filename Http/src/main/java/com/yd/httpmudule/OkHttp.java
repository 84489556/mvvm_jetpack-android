package com.yd.httpmudule;

import android.content.Context;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class OkHttp {

    private static final int DEFAULT_TIMEOUT = 15;
    private static OkHttpClient httpClient = null;
    private static OkHttp singleton = null;
    private Context context = null;

    private File cacheFile = null;
    private Cache cache = null;

    private HashMap<String, String> hostMap = null;
    private OkHttp() {
    }

    public void init(Context context, HashMap<String, String> hostMap) {
        cacheFile = new File(context.getExternalCacheDir(), "HttpCache");//缓存地址
        cache = new Cache(cacheFile, 1024 * 1024 * 50); //大小50Mb
        this.hostMap = hostMap;
    }

    public static OkHttp getInstance() {
        if (singleton == null) {
            synchronized (OkHttp.class) {
                singleton = new OkHttp();
            }

        }
        return singleton;
    }

    OkHttpClient getHttpClient() {

        if (httpClient == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(new LoggerInterceptor())
                    .addInterceptor(new HeaderInterceptor())
                    .addInterceptor(new UrlInterceptor(hostMap))
                    .cache(cache);
//                    .cookieJar(new OKHttpCookieManger());
//                  .addInterceptor(new CacheInterceptor());
            httpClient = httpClientBuilder.build();
        }
        return httpClient;
    }
}
