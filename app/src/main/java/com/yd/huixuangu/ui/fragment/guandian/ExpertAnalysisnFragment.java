package com.yd.huixuangu.ui.fragment.guandian;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.guandian.ExpertAnalysisViewModel;

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
