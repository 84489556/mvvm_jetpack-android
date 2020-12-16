package com.yd.huixuangu.base;

import android.util.Log;

import com.yd.httpmudule.YDYHttpApplication;
import com.yd.huixuangu.base.module.SocketModule;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.ISocketListener;

import okhttp3.Response;
import okhttp3.WebSocket;

public class WebSocketReceive implements ISocketListener {
    private static String TAG = "HuiXuanGuApplication";
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
        Log.d(TAG.concat(":onFailure = "), t.toString()+"");
    }
}
