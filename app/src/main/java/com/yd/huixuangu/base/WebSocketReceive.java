package com.yd.huixuangu.base;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.UiThread;

import com.yd.httpmudule.YDYHttpApplication;
import com.yd.huixuangu.base.module.Data;
import com.yd.huixuangu.base.module.SocketModule;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.websocket.YDYWebSocketManage;

import okhttp3.Response;
import okhttp3.WebSocket;

public class WebSocketReceive implements ISocketListener {
    private static String TAG = "HuiXuanGuApplication";
    public    String wss ="";

    public WebSocketReceive(String wss) {
        this.wss = wss;
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
            Data data = socketModule.getData();
            HuiXuanGuApplication.socketID = data.getUser().getId();
        }




        Log.d(TAG.concat(":onMessage = "), test);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {

        Log.d(TAG.concat(":onClosed = "), reason.toString());
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                YDYWebSocketManage.getInstance().connect(wss,WebSocketReceive.this);

            }
        });
    }
}
