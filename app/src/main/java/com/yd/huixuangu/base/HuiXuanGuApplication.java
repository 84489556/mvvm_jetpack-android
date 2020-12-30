package com.yd.huixuangu.base;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.bokecc.sdk.mobile.live.DWLiveEngine;
import com.yd.httpmudule.OkHttp;
import com.yd.huixuangu.net.http.HostManage;
import com.yd.huixuangu.net.socket.WebSocketReceive;
import com.yd.ydyun.websocket.YDYWebSocketManage;

public class HuiXuanGuApplication extends Application implements ViewModelStoreOwner {

    public static Application context;
    public static final String wss = "wss://yun.ydtg.com.cn?username=abc&password=123";
    public static String socketID = "";
    private ViewModelStore mAppViewModelStore;
    public  static Handler ApplicationHandler = new Handler(Looper.getMainLooper());

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
        mAppViewModelStore = new ViewModelStore();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
