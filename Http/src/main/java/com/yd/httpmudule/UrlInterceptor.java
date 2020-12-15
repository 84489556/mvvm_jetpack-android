package com.yd.httpmudule;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 适配多个host的情况
 */
public class UrlInterceptor implements Interceptor {

    private HashMap<String, String> hostMap;

    public UrlInterceptor(HashMap<String, String> hostMap) {
        this.hostMap = hostMap;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原始的originalRequest
        Request originalRequest = chain.request();
        //获取老的url
        HttpUrl oldUrl = originalRequest.url();
        //获取originalRequest的创建者builder
        Request.Builder builder = originalRequest.newBuilder();
        //获取头信息的集合如：manage,mdffx
        List<String> hostList = originalRequest.headers("host");
        if (hostList.size() > 0) {
            //删除原有配置中的值,就是namesAndValues集合里的值
            builder.removeHeader("host");
            //获取头信息中配置的value,如：manage或者mdffx
            String hostValue = hostList.get(0);
            HttpUrl baseURL = null;
            //根据头信息中配置的value,来匹配新的base_url地址
            String host = hostMap.get(hostValue);
            if (host!=null && !host.isEmpty()){
                baseURL = HttpUrl.parse(host);
            }
            if (baseURL == null) {
                return chain.proceed(originalRequest);
            }
            HttpUrl newHttpUrl = oldUrl.newBuilder()
                    .scheme(baseURL.scheme())//http协议如：http或者https
                    .host(baseURL.host())//主机地址
                    .port(baseURL.port())//端口
                    .build();
            //获取处理后的新newRequest
            Request newRequest = null;
            if (baseURL.host().equals("yun.ydtg.com.cn") || baseURL.host().equals("csyun-slb.yd.com.cn")) {
                newRequest = builder.url(newHttpUrl + YDYHttpApplication.YDYToken).build();
            }else {
                newRequest = builder.url(newHttpUrl).build();
            }
            return chain.proceed(newRequest);
        } else {
            return chain.proceed(originalRequest);
        }

    }
}
