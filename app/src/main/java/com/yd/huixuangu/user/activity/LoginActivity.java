package com.yd.huixuangu.user.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseActivity;
import com.yd.huixuangu.user.presenter.ILogin;
import com.yd.huixuangu.user.presenter.LoginPresenter;
import com.yd.ydyun.ISocketListener;
import com.yd.ydyun.websocket.YDYWebSocketManage;

import okhttp3.Response;
import okhttp3.WebSocket;


public class LoginActivity extends BaseActivity<LoginPresenter,LoginSocketPresenter> implements ILogin, ISocketListener {
    YDYWebSocketManage manage;
    String wss = "wss://yun.ydtg.com.cn?username=abc&password=123";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        YDYWebSocketManage.getInstance().connect(wss, this);

    }


    @Override
    public LoginSocketPresenter initSocketPresenter() {
        return new LoginSocketPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initView() {
        TextView textView = findViewById(R.id.textview);
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    public void success(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void shutdownWebSocket(View view) {
        if (manage != null) {
            manage.close();
        }

    }

    public void connect(View view) {
        if (manage != null) {
            manage.connect(wss,this);
        }
    }

    public void isConnect(View view) {
        Log.d("wgl", manage.isConnect() + "");
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String test) {

    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {

    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {

    }


}
