package com.yd.ydyun;

import okhttp3.Response;
import okhttp3.WebSocket;

public interface  ISocketListener {

     void onOpen(WebSocket webSocket,Response response);

     void onMessage(WebSocket webSocket, String test);

    void onClosed(WebSocket webSocket, int code, String reason);

     void onFailure(WebSocket webSocket, Throwable t, Response response);

}
