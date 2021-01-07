package com.yd.huixuangu.ui.fragment.guandian;

import com.yd.huixuangu.BR;
import com.yd.huixuangu.R;
import com.yd.huixuangu.base.BaseFragment;
import com.yd.huixuangu.base.DataBindingConfig;
import com.yd.huixuangu.viewmodel.guandian.NewsViewModel;

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
