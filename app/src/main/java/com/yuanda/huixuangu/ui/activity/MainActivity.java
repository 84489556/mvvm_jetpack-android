package com.yuanda.huixuangu.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.huixuangu.base.BaseActivity;
import com.yuanda.huixuangu.base.DataBindingConfig;
import com.yuanda.huixuangu.net.socket.SocketModule;
import com.yuanda.huixuangu.viewmodel.MainActivityViewModel;


public class MainActivity extends BaseActivity {
    private MainActivityViewModel mModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewModel() {
        mModel = getActivityScopeViewModel(MainActivityViewModel.class);
        Log.d("wgl activity ", mModel + "");


    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.mainModel, mModel);
    }


    public class EventHandler {
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onReceiveSocket(SocketModule data) {
        super.onReceiveSocket(data);
    }
}
