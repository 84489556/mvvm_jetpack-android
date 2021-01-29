package com.yuanda.cy_professional_select_stock.ui.activity;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseActivity;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.MainActivityViewModel;

public class TestActivity extends BaseActivity {
    private MainActivityViewModel mModel;
    @Override
    protected void initViewModel() {
        mModel = getActivityScopeViewModel(MainActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_test, BR.test, mModel).addBindingParam(BR.event, new EventHandler());
    }

    private class EventHandler {

    }
}
