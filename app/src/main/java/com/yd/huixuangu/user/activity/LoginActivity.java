package com.yd.huixuangu.user.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseActivity;
import com.yd.huixuangu.user.bean.ClassesBean;
import com.yd.huixuangu.user.bean.GaoguanjingmaishichangtongjiBean;
import com.yd.huixuangu.user.presenter.ILogin;
import com.yd.huixuangu.user.presenter.LoginPresenter2;


public class LoginActivity extends BaseActivity implements ILogin<GaoguanjingmaishichangtongjiBean, ClassesBean> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
    public void initPresenter() {
        LoginPresenter2 loginPresenter2 = new LoginPresenter2();
//        LoginPresenter loginPresenter = new LoginPresenter(this);
    }



    @Override
    public void success(GaoguanjingmaishichangtongjiBean msg) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_LONG).show();
    }
    @Override
    public void success2(ClassesBean results) {

    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }



    public void shutdownWebSocket(View view) {

    }

    public void connect(View view) {
    }

    public void isConnect(View view) {
    }

}
