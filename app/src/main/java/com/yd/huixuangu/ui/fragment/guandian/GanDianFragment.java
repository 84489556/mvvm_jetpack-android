package com.yd.huixuangu.ui.fragment.guandian;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.guandian.GuanDianFragmentViewModel;

public class GanDianFragment extends BaseFragment {

    GuanDianFragmentViewModel mViewModel;

    private static GanDianFragment daBangFragment;
    public static GanDianFragment getInstance(){
        if(daBangFragment == null){
            daBangFragment = new GanDianFragment();
        }
        return daBangFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_guandian, BR.dabang, mViewModel);

    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(GuanDianFragmentViewModel.class);
    }



}
