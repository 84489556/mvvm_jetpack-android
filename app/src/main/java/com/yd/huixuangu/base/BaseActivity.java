package com.yd.huixuangu.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public abstract void initPresenter();
    public abstract void initView();

}
