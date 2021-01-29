package com.yuanda.cy_professional_select_stock.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.bokecc.sdk.mobile.live.DWLiveEngine;
import com.yd.httpmudule.OkHttp;
import com.yuanda.cy_professional_select_stock.net.http.HostManage;
import com.yd.ydyun.websocket.YDYWebSocketManage;
import com.yuanda.cy_professional_select_stock.net.socket.WebSocketReceive;

import java.util.List;

public class HuiXuanGuApplication extends Application implements ViewModelStoreOwner {

    public static HuiXuanGuApplication context;
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


        String appProcessName = getAppProcessName(this);
        Log.d("wgl--",appProcessName);

    }



    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    public static HuiXuanGuApplication getInstance() {
        return context;
    }
}
