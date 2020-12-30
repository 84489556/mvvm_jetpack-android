package com.yd.huixuangu.ui.fragment.kanshi;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.kanshi.KanShiFragmentViewModel;

public class KanShiFragment extends BaseFragment {

    KanShiFragmentViewModel mViewModel;

    private static KanShiFragment kanShiFragment;
    public static KanShiFragment getInstance(){
        if(kanShiFragment == null){
            kanShiFragment = new KanShiFragment();
        }
        return kanShiFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_kanshi, BR.kanshi, mViewModel);

    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(KanShiFragmentViewModel.class);
    }



}
