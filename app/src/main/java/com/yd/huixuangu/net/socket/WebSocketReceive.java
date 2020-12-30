package com.yd.huixuangu.net.socket;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.yd.httpmudule.YDYHttpApplication;
import com.yd.huixuangu.base.HuiXuanGuApplication;
import com.yd.huixuangu.bean.Data;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.websocket.YDYWebSocketManage;

import okhttp3.Response;
import okhttp3.WebSocket;

public class WebSocketReceive implements ISocketListener {
    private static String TAG = "HuiXuanGuApplication";
    public  static String wss = "";
    Handler mainHandler;
    ReConnectRunnable runnable = null;

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
        listener.receiveSocket(socketModule);

    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {

        Log.d(TAG.concat(":onClosed = "), reason.toString());
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {

//        if (mainHandler == null){
//            mainHandler = new Handler(Looper.getMainLooper());
//        }
//
//
//        if (runnable == null){
//            runnable = new ReConnectRunnable();
//
//        }
//        mainHandler.post(runnable);
//        mainHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                YDYWebSocketManage.getInstance().connect(wss, WebSocketReceive.this);
//                ++anInt;
//                Log.d("wgl" ,anInt+"");
//
//            }
//        });

//        YDYWebSocketManage.getInstance().connect(wss, WebSocketReceive.this);
    }


    class ReConnectRunnable implements Runnable {
        public ReConnectRunnable() {
            ++anInt;
            Log.d("wgl" ,anInt+"");
            YDYWebSocketManage.getInstance().connect(wss, WebSocketReceive.this);
        }

        @Override
        public void run() {
            Log.d("wgl" ,anInt+"run");
            YDYWebSocketManage.getInstance().connect(wss, WebSocketReceive.this);

        }
    }
}
