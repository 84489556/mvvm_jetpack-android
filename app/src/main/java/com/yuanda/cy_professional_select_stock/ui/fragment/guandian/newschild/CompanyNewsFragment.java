package com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild;


import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.newschild.CompanyNewsViewModel;

/**
 * 资讯-公司新闻
 * */
public class CompanyNewsFragment extends BaseFragment {

    CompanyNewsViewModel companyNewsViewModel;

    private static CompanyNewsFragment companyNewsFragment;
    public static CompanyNewsFragment getInstance(){
        if(companyNewsFragment == null){
            companyNewsFragment = new CompanyNewsFragment();
        }
        return companyNewsFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_company_news, BR.cNewsViewModel, companyNewsViewModel);

    }

    @Override
    protected void initViewModel() {
        companyNewsViewModel = getFragmentScopeViewModel(CompanyNewsViewModel.class);
    }
}
