package com.yuanda.huixuangu.ui.fragment.guandian;

import com.yuanda.cy_professional_select_stock.BR;
import com.yuanda.cy_professional_select_stock.R;
import com.yuanda.huixuangu.base.BaseFragment;
import com.yuanda.huixuangu.base.DataBindingConfig;
import com.yuanda.huixuangu.viewmodel.guandian.NewsViewModel;

public class NewsFragment extends BaseFragment {

    NewsViewModel newsViewModel;

    private static NewsFragment newsFragment;
    public static NewsFragment getInstance(){
        if(newsFragment == null){
            newsFragment = new NewsFragment();
        }
        return newsFragment;
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_news, BR.news, newsViewModel);

    }

    @Override
    protected void initViewModel() {
        newsViewModel = getFragmentScopeViewModel(NewsViewModel.class);
    }
}
