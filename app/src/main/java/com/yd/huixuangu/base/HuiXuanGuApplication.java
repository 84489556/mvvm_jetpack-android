package com.yd.huixuangu.base;

import android.app.Application;
import android.content.Context;

import com.yd.httpmudule.OkHttp;

public class HuiXuanGuApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttp.getInstance().init(this, HostManage.getHostMap());
        context =   getApplicationContext();
    }

}
