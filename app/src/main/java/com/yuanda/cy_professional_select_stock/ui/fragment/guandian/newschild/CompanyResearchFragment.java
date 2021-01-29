package com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild;


import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.newschild.CompanyResearchViewModel;

/**
 * 资讯-公司研究
 * */
public class CompanyResearchFragment extends BaseFragment {

    CompanyResearchViewModel companyResearchViewModel;

    private static CompanyResearchFragment companyResearchFragment;
    public static CompanyResearchFragment getInstance(){
        if(companyResearchFragment == null){
            companyResearchFragment = new CompanyResearchFragment();
        }
        return companyResearchFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_company_research, BR.cResearchViewModel, companyResearchViewModel);

    }

    @Override
    protected void initViewModel() {
        companyResearchViewModel = getFragmentScopeViewModel(CompanyResearchViewModel.class);
    }
}
