package com.yd.ydyun.websocket;

import android.text.TextUtils;
import android.util.Log;

import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.module.EventType;

import java.util.HashSet;
import java.util.Set;

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
    private Set<String> eventTypeSet = new HashSet<String>();


    public static YDYWebSocketManage getInstance() {
        if (instance == null) {
            synchronized (YDYWebSocketManage.class) {
                instance = new YDYWebSocketManage();
            }
        }
        return instance;

    }

    public YDYWebSocketManage() {
        initEventTypeSet();
    }

    /**
     * 建立webSocket连接
     */
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


    /**
     * 监听某一个节点信息
     */

    public void subscribe( String msg) {
        WebSocket socket = WSSocketController.getSocket();
        if (socket != null) {
            Log.d("wgl sendMsg",msg);
            socket.send(msg);
        }

    }


    private void initEventTypeSet() {
        eventTypeSet.add(EventType.VALUE);
        eventTypeSet.add(EventType.CHILD_CHANGE);
        eventTypeSet.add(EventType.CHILD_REMOVE);
        eventTypeSet.add(EventType.CHILD_ADD);
    }

    private boolean useSet(String targetValue) {
        if (eventTypeSet != null) {
            return eventTypeSet.contains(targetValue);
        }
        return false;

    }

    /**
     * 关闭webSocket连接
     */
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
