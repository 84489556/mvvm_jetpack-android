package com.yd.ydyun.websocket;

import android.text.TextUtils;

import com.yd.ydyun.ISocketListener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class YDYWebSocketManage {

    private OkHttpClient client = null;
    private WebSocket webSocket = null;

    private String wss = "";
    private boolean isConnect = false;
    private YDYWebSocketController WSSocketController = null;
    private static YDYWebSocketManage instance = null;


    public static YDYWebSocketManage getInstance() {
        if (instance == null) {
            synchronized (YDYWebSocketManage.class) {
                instance = new YDYWebSocketManage();
            }
        }
        return instance;

    }

    public void connect(String wss, ISocketListener iSocketListener) {
        WSSocketController = new YDYWebSocketController(iSocketListener);
        if (!TextUtils.isEmpty(wss)) {
            this.wss = wss;
        }
        client = OkHttpClientInstance.getInstance().getClient();
        Request request = getRequestBuilder().url(wss).build();
        WSSocketController.setClose(false);
        webSocket = client.newWebSocket(request, WSSocketController);
    }

    public void close() {

        if (WSSocketController != null) {
            WSSocketController.setClose(true);
            WSSocketController.closeHeart();

            if (WSSocketController.getSocket() != null) {
                WSSocketController.getSocket().cancel();
                WSSocketController.getSocket().close(1001, "客户端主动关闭连接");
            }

        }
    }

    public void onDestroy() {
        if (null != client) {
            client = null;
        }
        if (webSocket != null) {
            webSocket = null;
        }
    }

    public boolean isConnect() {
        if (WSSocketController != null) {
            return WSSocketController.isConnect();
        }

        return false;

    }


    private Request.Builder getRequestBuilder() {
        return RequestBuilder.getInstance().getBuilder();
    }



    private String token = "";

    public String getToken() {
        return token;
    }
}
