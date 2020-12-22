package com.yd.huixuangu.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bokecc.sdk.mobile.live.DWLiveEngine;
import com.yd.httpmudule.OkHttp;
import com.yd.httpmudule.YDYHttpApplication;
import com.yd.huixuangu.base.module.SocketModule;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.websocket.YDYWebSocketManage;

import okhttp3.Response;
import okhttp3.WebSocket;

public class HuiXuanGuApplication extends Application  {

    public static Application context;
    public  static final String wss = "wss://yun.ydtg.com.cn?username=abc&password=123";
    public  static  String socketID = "";

    //public  static final String wss = "wss://csyun-slb.yd.com.cn?username=ydcyys589&password=555688";
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttp.getInstance().init(this, HostManage.getHostMap());
        context = this;
        YDYWebSocketManage.getInstance().connect(wss, new WebSocketReceive(wss));

        try {
            //迈视播放器
            DWLiveEngine.init(this);
        }catch (Exception e){

        }
    }

}
