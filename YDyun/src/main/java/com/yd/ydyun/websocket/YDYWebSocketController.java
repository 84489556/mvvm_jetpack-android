package com.yd.ydyun.websocket;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.module.HeartBean;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.module.WebSocketBaseResponse;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class YDYWebSocketController extends WebSocketListener {
    private WebSocket mSocket;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private static final long HEART_BEAT_RATE = 2 * 1000;
    private boolean isClose = false;
    private boolean isConnect = false;
    private ISocketListener iSocketListener;
    private static final String TAG = "websocket";

    WebSocket getSocket() {
        return mSocket;
    }

    YDYWebSocketController(ISocketListener iSocketListener) {
        super();
        if (iSocketListener != null) {
            this.iSocketListener = iSocketListener;
        }
    }

    /**
     * onClosed就是当连接已经释放的时候被回调
     */
    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosed(webSocket, code, reason);
        isConnect = false;
        if (null != iSocketListener) {
            iSocketListener.onClosed(webSocket, code, reason);
        }
        Log.d("wgl","onClosed");
    }

    /**
     * onClosing是当远程端暗示没有数据交互时回调
     * 此时准备关闭，但连接还没有关闭
     */
    @Override
    public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        super.onClosing(webSocket, code, reason);
        isConnect = false;
        if (null != iSocketListener) {
            iSocketListener.onClosed(webSocket, code, reason);
        }


    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
        super.onFailure(webSocket, t, response);
        isConnect = false;
        if (null != iSocketListener) {
            iSocketListener.onFailure(webSocket, t, response);
        }
        Log.d("wgl","onFailure");



    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        super.onMessage(webSocket, text);
        isConnect = true;
        if (null != iSocketListener) {
            iSocketListener.onMessage(webSocket, text);
        }
        WebSocketBaseResponse response = GsonSingle.getInstance().fromJson(text, WebSocketBaseResponse.class);

        if (response == null) {
            return;
        }

        WebSocketBaseResponse.Data data = response.getData();

        if (data == null) {
            return;
        }
        analysisEvent(data);
    }

    private void analysisEvent(WebSocketBaseResponse.Data data) {
        switch (data.getCode()){
            case 10007:
                break;
            case 1100:
            case 1104:
                break;
            case 1101:
            case 1102:
            case 1103:
                break;
            default:
        }
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
        super.onMessage(webSocket, bytes);
        isConnect = true;
        if (null != iSocketListener) {
            iSocketListener.onMessage(webSocket, bytes.toString());
        }

    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        super.onOpen(webSocket, response);
        this.mSocket = webSocket;
        sendHeartbeat(webSocket);
        isConnect = true;
        if (null != iSocketListener) {
            iSocketListener.onOpen(webSocket, response);
        }

    }


    /**
     * 国产心跳起搏器
     */
    private void sendHeartbeat(final WebSocket mSocket) {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!isClose()) {
                    mSocket.send(getHeart());
                    mHandler.postDelayed(this, HEART_BEAT_RATE);
                }
            }
        }, HEART_BEAT_RATE);

    }


    private String getHeart() {
        Gson gson = new Gson();
        return gson.toJson(HeartBean.getInstance());
    }


    void closeHeart() {
        isClose = true;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    private boolean isClose() {
        return isClose;
    }

    void setClose(boolean close) {
        isClose = close;
    }

    boolean isConnect() {
        return isConnect;
    }
}
