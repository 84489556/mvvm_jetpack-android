package com.yd.huixuangu.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.yd.httpmudule.OkHttp;
import com.yd.httpmudule.YDYHttpApplication;
import com.yd.huixuangu.base.module.SocketModule;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.websocket.YDYWebSocketManage;

import okhttp3.Response;
import okhttp3.WebSocket;

public class HuiXuanGuApplication extends Application implements ISocketListener {

    public static Context context;
    String wss = "wss://yun.ydtg.com.cn?username=abc&password=123";

    private static String TAG = "HuiXuanGuApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttp.getInstance().init(this, HostManage.getHostMap());
        context = getApplicationContext();
        YDYWebSocketManage.getInstance().connect(wss, this);

    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        Log.d(TAG.concat(":onOpen = "), response.toString());

    }

    @Override
    public void onMessage(WebSocket webSocket, String test) {
        SocketModule socketModule = GsonSingle.getInstance().fromJson(test, SocketModule.class);
        if (socketModule.getCode() == 10007) {
            YDYHttpApplication.YDYToken = socketModule.getToken();
        }

        Log.d(TAG.concat(":onMessage = "), socketModule.getToken());
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Log.d(TAG.concat(":onClosed = "), reason.toString());
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.d(TAG.concat(":onFailure = "), t.getMessage()+"");
    }
}
