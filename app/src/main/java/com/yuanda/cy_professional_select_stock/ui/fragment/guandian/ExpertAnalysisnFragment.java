package com.yuanda.cy_professional_select_stock.ui.fragment.guandian;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.cy_professional_select_stock.base.BaseFragment;
import com.yuanda.cy_professional_select_stock.base.DataBindingConfig;
import com.yuanda.cy_professional_select_stock.viewmodel.guandian.ExpertAnalysisViewModel;

public class ExpertAnalysisnFragment extends BaseFragment {

    ExpertAnalysisViewModel expertAnalysisViewModel;

    private static ExpertAnalysisnFragment expertAnalysisnFragment;
    public static ExpertAnalysisnFragment getInstance(){
        if(expertAnalysisnFragment == null){
            expertAnalysisnFragment = new ExpertAnalysisnFragment();
        }
        return expertAnalysisnFragment;
    }



    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_expertanalysis, BR.expertanalysis, expertAnalysisViewModel);

    }

    @Override
    protected void initViewModel() {
        expertAnalysisViewModel = getFragmentScopeViewModel(ExpertAnalysisViewModel.class);
    }
}
