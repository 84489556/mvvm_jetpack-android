package com.yd.huixuangu.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.yd.httpmudule.OkHttp;
import com.yd.huixuangu.net.http.HostManage;
import com.yd.huixuangu.net.socket.WebSocketReceive;
import com.yd.ydyun.websocket.YDYWebSocketManage;

public class HuiXuanGuApplication extends Application implements ViewModelStoreOwner {

    public static Application context;
    public static final String wss = "wss://yun.ydtg.com.cn?username=abc&password=123";
    public static String socketID = "";
    private ViewModelStore mAppViewModelStore;

    //public  static final String wss = "wss://csyun-slb.yd.com.cn?username=ydcyys589&password=555688";
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttp.getInstance().init(this, HostManage.getHostMap());
        context = this;
        YDYWebSocketManage.getInstance().connect(wss, new WebSocketReceive(wss));
        mAppViewModelStore = new ViewModelStore();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
