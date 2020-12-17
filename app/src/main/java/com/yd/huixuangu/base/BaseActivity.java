package com.yd.huixuangu.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yd.huixuangu.base.module.SocketModule;

public abstract class BaseActivity extends Activity implements WebSocketListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        WebSocketReceive.setListener(this);
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public abstract void initPresenter();

    public abstract void initView();


    public  void receiveSocket(SocketModule data){};
}
