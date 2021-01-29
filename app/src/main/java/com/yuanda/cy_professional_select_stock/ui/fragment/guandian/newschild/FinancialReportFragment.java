package com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild;


import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.newschild.FinancialReportViewModel;

/**
 * 资讯-财经报道
 * */
public class FinancialReportFragment extends BaseFragment {

    FinancialReportViewModel financialReportViewModel;

    private static FinancialReportFragment financialReportFragment;
    public static FinancialReportFragment getInstance(){
        if(financialReportFragment == null){
            financialReportFragment = new FinancialReportFragment();
        }
        return financialReportFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_financial_report, BR.freportViewModel, financialReportViewModel);

    }

    @Override
    protected void initViewModel() {
        financialReportViewModel = getFragmentScopeViewModel(FinancialReportViewModel.class);
    }
}
