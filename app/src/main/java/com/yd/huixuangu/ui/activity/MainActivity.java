package com.yd.huixuangu.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseActivity;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity {
    private MainViewModel mModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected void initViewModel() {
        mModel = getActivityScopeViewModel(MainViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_main, BR.mainModel, mModel)
                .addBindingParam(BR.event, new EventHandler());
    }


    public  class EventHandler  {
        public void button() {
            mModel.getRemoteData();
        }
    }

    public void init() {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


}