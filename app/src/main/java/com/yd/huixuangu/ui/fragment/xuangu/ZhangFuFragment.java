package com.yd.huixuangu.ui.fragment.xuangu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.bean.xuangu.BurstBean;
import com.yd.huixuangu.viewmodel.main.MainFragmentViewModel;
import com.yd.huixuangu.viewmodel.xuangu.XuanGuViewModel;
import com.yd.huixuangu.viewmodel.xuangu.ZhangFuViewModel;

/**
 * 涨幅空间
 */
public class ZhangFuFragment extends BaseFragment {
    private ZhangFuViewModel mViewModel;
    private final String TAG = ZhangFuFragment.class.getSimpleName();
    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_xuangu_zhangfu, BR.viewModel, mViewModel);
        bindingConfig.addBindingParam(BR.clickEvent,new ClickEventHandler());
        return bindingConfig;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initObserves();
    }
    private void initObserves(){
        mViewModel.burstLiveData.observe(getViewLifecycleOwner(), new Observer<BurstBean>() {
            @Override
            public void onChanged(BurstBean burstBean) {
                Log.i(TAG,burstBean.toString());
            }
        });
    }
    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(ZhangFuViewModel.class);
    }
    public class ClickEventHandler {
        public void onclick(){
            mViewModel.getBurstData();
        }
    }
}
