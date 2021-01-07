package com.yd.huixuangu.ui.fragment.guandian;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.guandian.HotFocusViewModel;

public class HotFocusFragment extends BaseFragment {

    HotFocusViewModel hotFocusViewModel;

    private static HotFocusFragment hotFocusFragment;
    public static HotFocusFragment getInstance(){
        if(hotFocusFragment == null){
            hotFocusFragment = new HotFocusFragment();
        }
        return hotFocusFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_hotfocus, BR.expertanalysis, hotFocusViewModel);

    }

    @Override
    protected void initViewModel() {
        hotFocusViewModel = getFragmentScopeViewModel(HotFocusViewModel.class);
    }
}
