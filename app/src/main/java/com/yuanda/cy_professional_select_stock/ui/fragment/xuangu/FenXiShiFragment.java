package com.yuanda.cy_professional_select_stock.ui.fragment.xuangu;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.main.MainFragmentViewModel;

/**
 * 明星分析师推荐
 */
public class FenXiShiFragment extends BaseFragment {
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
