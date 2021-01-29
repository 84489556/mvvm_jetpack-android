package com.yuanda.cy_professional_select_stock.ui.fragment.dabang;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.dabang.LongHuBangFragmentViewModel;

/**
 * 龙虎榜.
 */
public class LongHuBangFragment extends BaseFragment {

    LongHuBangFragmentViewModel mViewModel;

    public LongHuBangFragment() {
        // Required empty public constructor
    }
    public static LongHuBangFragment newInstance(String param1, String param2) {
        LongHuBangFragment fragment = new LongHuBangFragment();

        return fragment;
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_long_hu_bang, BR.longHuBang, mViewModel);
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(LongHuBangFragmentViewModel.class);
    }
}