package com.yd.huixuangu.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.MainFragmentViewModel;
import com.yd.huixuangu.viewmodel.MainViewModel;

public class MainFragment extends BaseFragment {
    private MainFragmentViewModel mState;

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_main, BR.mainFragmentModel, mState);
        bindingConfig.addBindingParam(BR.callback, new MainFragmentCallBack());
        return bindingConfig;
    }

    @Override
    protected void initViewModel() {

        mState = getFragmentScopeViewModel(MainFragmentViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class MainFragmentCallBack {

        public void openSecondFragment() {

            Bundle bundle = new Bundle();
            bundle.putString("key", "来自mainfragment的数据");
            nav().navigate(R.id.secondFragment, bundle);
        }
    }
}
