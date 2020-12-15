package com.yd.huixuangu.base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public abstract class BaseActivity<T extends BasePresenter> extends Activity {
    protected T p;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        p = initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p!=null){
            p.onDestroy();
            p = null;
        }

    }


    public abstract void initView();

    public abstract T initPresenter();

    public T getPresenter() {
        return p;
    }

    public void setPresenter(T presenter) {
        this.p = presenter;
    }
}
