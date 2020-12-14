package com.yd.ydyun.websocket;

import okhttp3.Request;

public class RequestBuilder {
    private static Request.Builder requestBuilder = null;
    private static RequestBuilder instance = null;

    public static RequestBuilder getInstance() {
        if (instance == null) {
            synchronized (RequestBuilder.class) {
                instance  = new RequestBuilder();
            }

        }
        return instance;
    }
    public  Request.Builder getBuilder(){
        if (requestBuilder == null) {
            requestBuilder = new Request.Builder();
        }
        return requestBuilder;
    }

}
