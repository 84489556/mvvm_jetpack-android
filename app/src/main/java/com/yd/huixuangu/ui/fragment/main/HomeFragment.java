package com.yd.huixuangu.ui.fragment.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.main.HomeFragmentViewModel;

public class HomeFragment extends BaseFragment {
    private HomeFragmentViewModel mViewModel;
    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_home, BR.home, mViewModel);
        bindingConfig.addBindingParam(BR.callback, new HomeFragmentCallBack());
        return bindingConfig;
    }

    @Override
    protected void initViewModel() {

        mViewModel = getFragmentScopeViewModel(HomeFragmentViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class HomeFragmentCallBack {


      public void   openSecondFragment(){

          nav().navigate(R.id.secondFragment);
        }


    }
}
