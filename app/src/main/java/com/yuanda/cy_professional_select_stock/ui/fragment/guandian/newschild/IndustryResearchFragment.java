package com.yuanda.cy_professional_select_stock.ui.fragment.guandian.newschild;


import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.newschild.IndustryResearchViewModel;

/**
 * 资讯-行业研究
 * */
public class IndustryResearchFragment extends BaseFragment {

    IndustryResearchViewModel industryResearchViewModel;

    private static IndustryResearchFragment industryResearchFragment;
    public static IndustryResearchFragment getInstance(){
        if(industryResearchFragment == null){
            industryResearchFragment = new IndustryResearchFragment();
        }
        return industryResearchFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_company_industry, BR.iResearchViewModel, industryResearchViewModel);

    }

    @Override
    protected void initViewModel() {
        industryResearchViewModel = getFragmentScopeViewModel(IndustryResearchViewModel.class);
    }
}
