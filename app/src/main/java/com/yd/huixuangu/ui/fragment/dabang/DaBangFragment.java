package com.yd.huixuangu.ui.fragment.dabang;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.dabang.DaBangFragmentViewModel;

public class DaBangFragment extends BaseFragment {

    DaBangFragmentViewModel mViewModel;

    private static DaBangFragment daBangFragment;
    public static DaBangFragment getInstance(){
        if(daBangFragment == null){
            daBangFragment = new DaBangFragment();
        }
        return daBangFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_dabang, BR.dabang, mViewModel);

    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(DaBangFragmentViewModel.class);
    }



}
