package com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild;


import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.newschild.OptionalStockViewModel;

/**
 * 资讯-自选股
 * */
public class OptionalStockFragment extends BaseFragment {

    OptionalStockViewModel optionalStockViewModel;

    private static OptionalStockFragment optionalStockFragment;
    public static OptionalStockFragment getInstance(){
        if(optionalStockFragment == null){
            optionalStockFragment = new OptionalStockFragment();
        }
        return optionalStockFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_optional_stock, BR.oStockViewModel, optionalStockViewModel);

    }

    @Override
    protected void initViewModel() {
        optionalStockViewModel = getFragmentScopeViewModel(OptionalStockViewModel.class);
    }
}
