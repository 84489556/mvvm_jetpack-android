package com.yd.huixuangu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yd.huixuangu.net.socket.SocketModule;
import com.yd.huixuangu.net.socket.WebSocketListener;
import com.yd.huixuangu.net.socket.WebSocketReceive;

public abstract class BaseActivity extends AppCompatActivity implements WebSocketListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebSocketReceive.setListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void receiveSocket(SocketModule data) {
    }

}
