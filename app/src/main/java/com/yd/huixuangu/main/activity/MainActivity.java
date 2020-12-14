package com.yd.huixuangu.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseActivity;
import com.yd.huixuangu.main.presenter.IMain;
import com.yd.huixuangu.main.presenter.MainPresenter;
import com.yd.huixuangu.user.activity.LoginActivity;


public class MainActivity extends BaseActivity<MainPresenter> implements IMain {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void testButton(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }


    @Override
    public void initView() {

    }


    @Override
    public MainPresenter initPresenter() {
        return null;
    }

    @Override
    public void success(String msg) {

    }

    @Override
    public void failure(String msg) {

    }
}
