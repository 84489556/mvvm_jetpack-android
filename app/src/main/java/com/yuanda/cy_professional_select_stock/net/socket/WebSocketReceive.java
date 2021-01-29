package com.yuanda.cy_professional_select_stock.net.socket;

import android.util.Log;

import com.yd.httpmudule.YDYHttpApplication;
import com.yuanda.cy_professional_select_stock.base.HuiXuanGuApplication;
import com.yuanda.cy_professional_select_stock.bean.Data;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.websocket.YDYWebSocketManage;

import okhttp3.Response;
import okhttp3.WebSocket;

public class WebSocketReceive implements ISocketListener {
    private static String TAG = "HuiXuanGuApplication";
    public  static String wss = "";

    int anInt =0;
    public WebSocketReceive(String wss) {
        this.wss = wss;
    }

    private static WebSocketListener listener;

    public static void setListener(WebSocketListener mListener) {
        listener = mListener;
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
        if (socketModule.getData() == null) return;
        if (socketModule.getData().getNodePath() == null) return;
        if (listener == null) return;
        listener.onReceiveSocket(socketModule);

    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {

        Log.d(TAG.concat(":onClosed = "), reason.toString());
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        HuiXuanGuApplication.ApplicationHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                YDYWebSocketManage.getInstance().connect(wss, WebSocketReceive.this);
            }
        },1800);
    }

}
