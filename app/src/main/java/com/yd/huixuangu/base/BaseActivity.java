package com.yd.huixuangu.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseActivity<T extends BasePresenter,YDYP> extends Activity {
    protected T p;

    protected YDYP ydyp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        p = initPresenter();
        ydyp = initSocketPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.onDestroy();
        p = null;
        ydyp = null;
    }


    public abstract void initView();

    public abstract T initPresenter();
    public  YDYP initSocketPresenter(){
        return ydyp;
    };

    public T getPresenter() {
        return p;
    }

    public void setPresenter(T presenter) {
        this.p = presenter;
    }
}
