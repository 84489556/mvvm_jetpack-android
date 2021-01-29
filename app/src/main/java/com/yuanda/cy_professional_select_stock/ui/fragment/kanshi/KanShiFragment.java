package com.yuanda.cy_professional_select_stock.ui.fragment.kanshi;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.kanshi.KanShiFragmentViewModel;

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
