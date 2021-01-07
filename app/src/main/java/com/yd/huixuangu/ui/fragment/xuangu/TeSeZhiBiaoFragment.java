package com.yd.huixuangu.ui.fragment.xuangu;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.main.MainFragmentViewModel;

/**
 * 特色指标
 */
public class TeSeZhiBiaoFragment extends BaseFragment {
    private MainFragmentViewModel mViewModel;
    @Override
    protected DataBindingConfig getDataBindingConfig() {
        DataBindingConfig bindingConfig = new DataBindingConfig(R.layout.fragment_main, BR.mainFragmentModel, mViewModel);
        return bindingConfig;
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(MainFragmentViewModel.class);
    }
}
