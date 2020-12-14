package com.yd.ydyun.websocket;

import android.text.TextUtils;

import com.yd.ydyun.GsonSingle;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.QueryModule;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;

public class YDYWebSocketManage {

    private OkHttpClient client = null;
    private WebSocket webSocket = null;
    private String wss = "";
    private boolean isConnect = false;
    private YDYWebSocketController WSSocketController = null;
    private static  YDYWebSocketManage instance= null;



    public static YDYWebSocketManage getInstance(){
        if (instance == null){
            synchronized (YDYWebSocketManage.class){
                instance = new YDYWebSocketManage();
            }
        }
        return instance;

    }

    public void test() {
        QueryModule.Builder builder = new QueryModule.Builder().orderByKey("");
        GsonSingle.getInstance().toJson(builder);


        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "admin")
                .build();
        final Request request = new Request.Builder()
                .url("https://" + "ip" + "/node/sdkapi?token=" + "token")
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
            }
        });
    }


    public void connect(String wss,ISocketListener iSocketListener) {
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

}
