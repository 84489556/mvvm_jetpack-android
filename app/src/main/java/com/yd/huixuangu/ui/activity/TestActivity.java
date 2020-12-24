package com.yd.huixuangu.ui.activity;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseActivity;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.MainViewModel;

public class TestActivity extends BaseActivity {
    private MainViewModel mModel;
    @Override
    protected void initViewModel() {
        mModel = getActivityScopeViewModel(MainViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_test, BR.test, mModel).addBindingParam(BR.event, new EventHandler());
    }

    private class EventHandler {

    }
}
