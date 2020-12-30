package com.yd.huixuangu.ui.fragment.xuangu;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.xuangu.XuanGuFragmentViewModel;

public class XuanGuFragment extends BaseFragment {

    XuanGuFragmentViewModel mViewModel;

    private static XuanGuFragment xuanGuFragment;

    public static XuanGuFragment getInstance() {
        if (xuanGuFragment == null) {
            xuanGuFragment = new XuanGuFragment();
        }
        return xuanGuFragment;
    }


    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_xuangu, BR.xuangu, mViewModel);

    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(XuanGuFragmentViewModel.class);
    }


}
