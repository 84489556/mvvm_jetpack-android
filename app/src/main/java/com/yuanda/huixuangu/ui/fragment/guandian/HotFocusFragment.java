package com.yuanda.huixuangu.ui.fragment.guandian;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.huixuangu.base.BaseFragment;
import com.yuanda.huixuangu.base.DataBindingConfig;
import com.yuanda.huixuangu.viewmodel.guandian.HotFocusViewModel;

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
