package com.yd.huixuangu.ui.fragment.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.test.VideoTestActivity;
import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.main.MainFragmentViewModel;

public class MainFragment extends BaseFragment {
    private MainFragmentViewModel mViewModel;


    private static MainFragment mainFragment;
    public static MainFragment getInstance(){
        if(mainFragment == null){
            mainFragment = new MainFragment();
        }
        return mainFragment;
    }


    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_main, BR.mainFragmentModel, mViewModel);
        bindingConfig.addBindingParam(BR.callback, new MainFragmentCallBack());
        return bindingConfig;
    }

    @Override
    protected void initViewModel() {

        mViewModel = getFragmentScopeViewModel(MainFragmentViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class MainFragmentCallBack {


    }
}
